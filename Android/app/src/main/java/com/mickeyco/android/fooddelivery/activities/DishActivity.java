package com.mickeyco.android.fooddelivery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.DishFragment;

public class DishActivity extends SingleFragmentActivity {

    private static final String EXTRA_DISH_ID = "dish_id";
    private static final String EXTRA_CATEGORY_ID = "category_id";
    private int mDishId;
    private int mCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDishId = getIntent().getIntExtra(EXTRA_DISH_ID, 0);
        mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Fragment createFragment() {
        return DishFragment.newInstance(mDishId);
    }


    public static Intent newIntent(Context packageContext, int dishId, int categoryId){
        Intent intent = new Intent(packageContext, DishActivity.class);
        intent.putExtra(EXTRA_DISH_ID, dishId);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = DishListActivity.newIntent(this, mCategoryId);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }


}
