package com.mickeyco.android.fooddelivery.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.CartFragment;

/**
 * Created by Softteco on 02.05.2017.
 */

public class CartActivity extends SingleFragmentActivity implements CartFragment.Callbacks{
    @Override
    protected Fragment createFragment() {
        return new CartFragment();
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


    @Override
    public void onButtonClicked(int totalPrice) {
        startActivity(OrderAcceptActivity.createIntent(this, totalPrice));
    }
}
