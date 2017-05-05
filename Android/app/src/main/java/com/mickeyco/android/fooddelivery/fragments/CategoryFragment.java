package com.mickeyco.android.fooddelivery.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.Category;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class CategoryFragment extends Fragment {
    private ProgressBar mProgressBar;
    private RecyclerView mCategoryRecyclerView;
    private Callbacks mCallbacks;
    private static ArrayList<Category> mCategories;

    public interface Callbacks{
        void onCategorySelected(int categoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        mCategoryRecyclerView = (RecyclerView) rootView.findViewById(R.id.category_recycler_view);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.category_progress_bar);
        getCategories();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

    private void getCategories() {
        RequestInterface requestInterface = ApiFactory.getService();
        final Call<ArrayList<Category>> response = requestInterface.getAllCategories();

        response.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, retrofit2.Response<ArrayList<Category>> response) {
                mCategories = response.body();
                mProgressBar.setVisibility(View.INVISIBLE);
                setupAdapter();
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private class CategoryHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private TextView mCategoryName;
        private ImageView mCategoryImage;
        private Category mCategory;

        public CategoryHolder(View itemView) {
            super(itemView);
            mCategoryName = (TextView) itemView.findViewById(R.id.category_name);
            mCategoryImage = (ImageView) itemView.findViewById(R.id.category_image);
            itemView.setOnClickListener(this);
        }

        public void bindCategory(Category category){
            mCategory = category;
            mCategoryName.setText(mCategory.getName());
//            String url = mCategory.getUrl();
            Picasso.with(getActivity()).load(Constants.CATEGORY_IMAGE_URL).fit().into(mCategoryImage);
        }

        @Override
        public void onClick(View view) {
            mCallbacks.onCategorySelected(mCategory.getId());
        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder>{

        private List<Category> mCategories;

        public CategoryAdapter(List<Category> categories) {
            mCategories = categories;
        }

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.category_list_item, parent, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
            Category category = mCategories.get(position);
            holder.bindCategory(category);
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }

    private void setupAdapter() {
        if (getActivity() == null || mCategoryRecyclerView == null) {
            return;
        }
        if (mCategories != null) {
            mCategoryRecyclerView.setAdapter(new CategoryAdapter(mCategories));
            mCategoryRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mCategoryRecyclerView.setAdapter(null);
        }

    }
    
}
