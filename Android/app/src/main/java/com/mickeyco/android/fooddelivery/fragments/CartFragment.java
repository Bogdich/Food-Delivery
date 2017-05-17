package com.mickeyco.android.fooddelivery.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.Category;
import com.mickeyco.android.fooddelivery.api.models.Dish;
import com.mickeyco.android.fooddelivery.utils.CartDishes;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Softteco on 02.05.2017.
 */

public class CartFragment extends Fragment {
    private RecyclerView mCartRecyclerView;
    private ArrayList<Category> mCategories;
    private ProgressBar mProgressBar;
    private Button mCreateOrderBtn;
    private int mTotalPrice;
    private TextView mEmptyText;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onButtonClicked(int totalPrice);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartRecyclerView = (RecyclerView) rootView.findViewById(R.id.cart_recycler_view);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        mCartRecyclerView.addItemDecoration(divider);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.cart_progress_bar);
        getCategories();
        mCreateOrderBtn = (Button) rootView.findViewById(R.id.create_order_btn);
        mEmptyText = (TextView) rootView.findViewById(R.id.empty_cart_text);
        (rootView.findViewById(R.id.create_order_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onButtonClicked(mTotalPrice);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void getCategories() {
        RequestInterface requestInterface = ApiFactory.getService();
        final Call<ArrayList<Category>> response = requestInterface.getAllCategories();

        response.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, retrofit2.Response<ArrayList<Category>> response) {
                mCategories = response.body();
                mProgressBar.setVisibility(View.INVISIBLE);
                if (mCategories.isEmpty()) {
                    mEmptyText.setVisibility(View.VISIBLE);
                } else {
                    mCreateOrderBtn.setVisibility(View.VISIBLE);
                    mCartRecyclerView.setAdapter(new CartAdapter(CartDishes.getInstance().getDishes()));
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private class CartHolder extends RecyclerView.ViewHolder {

        private TextView mDishName;
        private TextView mCategoryName;
        private TextView mDishAmount;
        private TextView mDishPrice;
        private Dish mDish;
        private Integer mAmount;
        private ImageView mBgImage;


        public CartHolder(View itemView) {
            super(itemView);
            mDishName = (TextView) itemView.findViewById(R.id.dish_name_TV);
            mCategoryName = (TextView) itemView.findViewById(R.id.category_name_TV);
            mDishAmount = (TextView) itemView.findViewById(R.id.dish_amount_text);
            mDishPrice = (TextView) itemView.findViewById(R.id.dish_price_text);
            mBgImage = (ImageView) itemView.findViewById(R.id.cart_item_bg_image);
            (itemView.findViewById(R.id.cart_minus_amount_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAmount != 0) {
                        mAmount--;
                        mDishAmount.setText(mAmount + "x");
                        CartDishes.getInstance().addDish(mDish, mAmount);
                        mTotalPrice -= mDish.getPrice();
                        mCreateOrderBtn.setText("Итого: " + mTotalPrice + " BYN\nОформить заказ");
                    }
                }
            });

            (itemView.findViewById(R.id.cart_plus_amount_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAmount != 10) {
                        mAmount++;
                        mDishAmount.setText(mAmount + "x");
                        CartDishes.getInstance().addDish(mDish, mAmount);
                        mTotalPrice += mDish.getPrice();
                        mCreateOrderBtn.setText("Итого: " + mTotalPrice + " BYN\nОформить заказ");
                    }
                }
            });
        }

        public void bindCart(Dish dish, Integer amount){
            mDish = dish;
            mAmount = amount;
            mDishName.setText(mDish.getName());
            for (Category category: mCategories) {
                if (category.getId() - 1 == mDish.getCategoryId()) {
                    mCategoryName.setText(category.getName());
                    Picasso.with(getActivity()).load(category.getImageUrl()).fit().into(mBgImage);
                }
            }
            mDishAmount.setText(mAmount + "x");
            mDishPrice.setText(mDish.getPrice() + " BYN");
            mTotalPrice += dish.getPrice() * amount;
            mCreateOrderBtn.setText("Итого: " + mTotalPrice + " BYN\nОформить заказ");
        }
    }

    private class CartAdapter extends RecyclerView.Adapter<CartFragment.CartHolder>{

        private ArrayList<Dish> mDishesList;
        private HashMap<Dish, Integer> mDishesMap;

        public CartAdapter(HashMap<Dish, Integer> dishes) {
            mDishesMap = dishes;
            mDishesList = new ArrayList<>(dishes.keySet());
            if (mDishesList.isEmpty()) {
                mEmptyText.setVisibility(View.VISIBLE);
                mCreateOrderBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public CartFragment.CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.cart_list_item, parent, false);
            return new CartFragment.CartHolder(view);
        }

        @Override
        public void onBindViewHolder(CartFragment.CartHolder holder, int position) {
            Dish dish = mDishesList.get(position);
            holder.bindCart(dish, mDishesMap.get(dish));
        }

        @Override
        public int getItemCount() {
            return mDishesMap.size();
        }
    }
}
