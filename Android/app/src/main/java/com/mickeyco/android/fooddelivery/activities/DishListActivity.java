package com.mickeyco.android.fooddelivery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.DishListFragment;

public class DishListActivity extends SingleFragmentActivity
    implements DishListFragment.Callbacks{
    private static final String EXTRA_CATEGORY_ID = "com.mickeyco.android.foodservice.category_id";
    private int mCategoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Fragment createFragment() {
        return DishListFragment.newInstance(mCategoryId);
    }

    public static Intent newIntent(Context packageContext, int categoryId){
        Intent intent = new Intent(packageContext, DishListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    public void onDishSelected(int dishId) {
        Intent intent = DishActivity.newIntent(this, dishId, mCategoryId);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, CategoryActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
