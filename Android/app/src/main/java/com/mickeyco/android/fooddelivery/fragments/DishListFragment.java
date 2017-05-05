package com.mickeyco.android.fooddelivery.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mickeyco.android.fooddelivery.R;

import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.Category;
import com.mickeyco.android.fooddelivery.api.models.Dish;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class DishListFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "category_id";
    private Callbacks mCallbacks;
    private RecyclerView mRecyclerView;
    private ArrayList<Dish> mDishes;
    private ProgressBar mProgressBar;

    public interface Callbacks{
        void onDishSelected(int DishId);
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

    private void getDishesByCategoryId(int id) {
        RequestInterface requestInterface = ApiFactory.getService();
        final Call<ArrayList<Dish>> response = requestInterface.getDishesByCategoryId(id);
        response.enqueue(new Callback<ArrayList<Dish>>() {
            @Override
            public void onResponse(Call<ArrayList<Dish>> call, retrofit2.Response<ArrayList<Dish>> response) {
                mDishes = response.body();
                mProgressBar.setVisibility(View.INVISIBLE);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(new DishAdapter(mDishes));
            }

            @Override
            public void onFailure(Call<ArrayList<Dish>> call, Throwable t) {
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.dishes_progress_bar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_dish_recycler_view);
        getDishesByCategoryId(getArguments().getInt(ARG_CATEGORY_ID));
        return view;
    }

    public static DishListFragment newInstance(int categoryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, categoryId);
        DishListFragment fragment = new DishListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class DishHolder extends RecyclerView.ViewHolder{

        private ImageView mDishImage;
        private TextView mDishName;
        private TextView mDishPrice;
        private Dish mDish;

        public DishHolder(View itemView) {
            super(itemView);
            mDishImage = (ImageView) itemView.findViewById(R.id.dish_image);
            mDishName = (TextView) itemView.findViewById(R.id.dish_name);
            mDishPrice = (TextView) itemView.findViewById(R.id.dish_price);
        }

        public void bindDish(Dish Dish){
            mDish = Dish;
            Picasso.with(getContext()).load(mDish.getImageUrl()).fit().into(mDishImage);
            mDishName.setText(mDish.getName());
            mDishPrice.setText(mDish.getPrice() + " BYN");
        }
    }

    private class DishAdapter extends RecyclerView.Adapter<DishHolder>{

        private List<Dish> mDishes;

        public final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildAdapterPosition(view);
                Dish Dish = mDishes.get(itemPosition);
                mCallbacks.onDishSelected(Dish.getId());
            }
        };

        public DishAdapter(List<Dish> Dishes){
            mDishes = Dishes;
        }

        @Override
        public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dish_list_item, parent, false);
            view.setOnClickListener(mOnClickListener);
            return new DishHolder(view);
        }

        @Override
        public void onBindViewHolder(DishHolder holder, int position) {
            Dish Dish = mDishes.get(position);
            holder.bindDish(Dish);
        }

        @Override
        public int getItemCount() {
            return mDishes.size();
        }
    }




}
