package com.mickeyco.android.fooddelivery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.Dish;
import com.mickeyco.android.fooddelivery.utils.CartDishes;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;

public class DishFragment extends Fragment {
    private static final String ARG_DISH_ID = "dish_id";
    private Dish mDish;
    private ImageView mDishImage;
    private TextView mDishName;
    private TextView mDishPrice;
    private TextView mDishDescription;
    private TextView mDishWeight;
    private TextView mDishAmount;
    private int mAmount;

    public static DishFragment newInstance(int dishId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DISH_ID, dishId);

        DishFragment fragment = new DishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getDishById(int dishId) {
        RequestInterface requestInterface = ApiFactory.getService();
        final Call<Dish> response = requestInterface.getDishById(dishId);

        response.enqueue(new Callback<Dish>() {
            @Override
            public void onResponse(Call<Dish> call, retrofit2.Response<Dish> response) {
                mDish = response.body();
                updateViews();
            }

            @Override
            public void onFailure(Call<Dish> call, Throwable t) {
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void updateViews() {
        Picasso.with(getContext()).load(Constants.CATEGORY_IMAGE_URL).into(mDishImage);
        mDishName.setText(mDish.getName());
        mDishPrice.setText(mDish.getPrice() + " BYN");
        mDishDescription.setText(mDish.getDescription());
        mDishWeight.setText(mDish.getWeight() + " г.");
//        mDishWeight.setText("344 г.");
        mDishAmount.setText(mAmount + " шт.");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dish, container, false);
        int dishId = getArguments().getInt(ARG_DISH_ID);
        getDishById(dishId);

        mDishImage = (ImageView) view.findViewById(R.id.dish_image);
        mDishName = (TextView) view.findViewById(R.id.dish_name);
        mDishPrice = (TextView) view.findViewById(R.id.dish_price);
        mDishDescription = (TextView) view.findViewById(R.id.dish_description_TV);
        mDishWeight = (TextView) view.findViewById(R.id.dish_weight_TV);
        mDishAmount = (TextView) view.findViewById(R.id.dish_amount_TV);
        (view.findViewById(R.id.dish_minus_amount_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAmount != 0) {
                    mAmount--;
                    mDishAmount.setText(mAmount + " шт.");
                }
            }
        });

        (view.findViewById(R.id.dish_plus_amount_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAmount != 10) {
                    mAmount++;
                    mDishAmount.setText(mAmount + " шт.");
                }
            }
        });

        (view.findViewById(R.id.add_to_cart_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAmount != 0) {
                    CartDishes.getInstance().addDish(mDish, mAmount);
                    Toast.makeText(getActivity(), "Успешно добавлено в корзину!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;

    }
}
