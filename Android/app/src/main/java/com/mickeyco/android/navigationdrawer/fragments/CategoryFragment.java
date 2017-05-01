package com.mickeyco.android.navigationdrawer.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickeyco.android.navigationdrawer.R;
import com.mickeyco.android.navigationdrawer.database.realm.category.CategoryHelper;
import com.mickeyco.android.navigationdrawer.database.realm.offer.OfferHelper;
import com.mickeyco.android.navigationdrawer.food_menu.realm.Category;
import com.mickeyco.android.navigationdrawer.utils.Constants;
import com.mickeyco.android.navigationdrawer.utils.RealmMenuXmlPullParser;

import java.util.List;

import io.realm.Realm;

public class CategoryFragment extends Fragment {
    private ProgressDialog mProgressDialog;

//    private Menu menu;
    private RecyclerView mCategoryRecyclerView;
    private Callbacks mCallbacks;

    public interface Callbacks{
        void onCategorySelected(int categoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(Constants.logTag, "OnCreateView - CategoryFragment");
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        mCategoryRecyclerView = (RecyclerView) rootView.findViewById(R.id.crime_recycler_view);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryRecyclerView.setVisibility(View.INVISIBLE);
        setupAdapter();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.logTag, "OnCreate - CategoryFragment");
        setRetainInstance(true);
        mProgressDialog = new ProgressDialog(getContext());
//        menu = Menu.getInstance(getContext());
//        if(menu.getCategories().isEmpty())
//            new AsyncMenuParser().execute(true);
        if(CategoryHelper.get(Realm.getDefaultInstance()).isEmpty())
            new AsyncMenuParser().execute(true);
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



    private class CategoryHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private TextView mCategoryName;
        private ImageView mCategoryImage;
        private Category mCategory;

        public CategoryHolder(View itemView) {
            super(itemView);
            mCategoryName = (TextView) itemView.findViewById(R.id.category_name);
            mCategoryImage = (ImageView) itemView.findViewById(R.id.category_icon);
            itemView.setOnClickListener(this);
        }

        public void bindCategory(Category category){
            mCategory = category;
            mCategoryName.setText(mCategory.getName());
        }

        @Override
        public void onClick(View view) {
//            Menu menu = Menu.getInstance(getContext());
            String categoryName = ((TextView) view.findViewById(R.id.category_name)).getText().toString();
            mCallbacks.onCategorySelected(CategoryHelper.getByName(Realm.getDefaultInstance(), categoryName).getCategoryId());
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
        Log.d(Constants.logTag, "SetupAdapter");
        if (getActivity() == null || mCategoryRecyclerView == null) {
            Log.d(Constants.logTag, "SetAdapter - failed(smth is null)");
            return;
        }
        if (!CategoryHelper.get(Realm.getDefaultInstance()).isEmpty()) {
            Log.d(Constants.logTag, "SetAdapter = CategoryAdapter");
            mCategoryRecyclerView.setAdapter(new CategoryAdapter(CategoryHelper.get(Realm.getDefaultInstance())));
            mCategoryRecyclerView.setVisibility(View.VISIBLE);
            if(OfferHelper.get(Realm.getDefaultInstance()).isEmpty())
                new AsyncMenuParser().execute(false);
        } else {
            Log.d(Constants.logTag, "SetAdapter = null");
            mCategoryRecyclerView.setAdapter(null);
        }

    }

    private class AsyncMenuParser extends AsyncTask<Boolean, Void, Void>{
        private boolean isCategoryParser;

        @Override
        protected Void doInBackground(Boolean... booleen) {
            Log.d(Constants.logTag, "doInBackground");
            isCategoryParser = booleen[0];
            if(isCategoryParser)
//                new SQLMenuXmlPullParser(getContext()).parseCategories();
                new RealmMenuXmlPullParser(getContext()).parseCategories();
            else
//                new SQLMenuXmlPullParser(getContext()).parseOffers();
                new RealmMenuXmlPullParser(getContext()).parseOffers();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(Constants.logTag, "OnPreExecute");
            mProgressDialog.setMessage("Downloading...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(Constants.logTag, "OnPostExecute");
            mProgressDialog.cancel();
            if(isCategoryParser)
                setupAdapter();
        }
    }

    
}
