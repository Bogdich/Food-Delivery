package com.mickeyco.android.fooddelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.CategoryFragment;

public class CategoryActivity extends SingleFragmentActivity
    implements CategoryFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new CategoryFragment();
    }


    @Override
    public void onCategorySelected(int categoryId) {
        Intent intent = DishListActivity.newIntent(this, categoryId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
}
