package com.mickeyco.android.fooddelivery.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.OrderAcceptFragment;

/**
 * Created by Softteco on 08.05.2017.
 */

public class OrderAcceptActivity extends SingleFragmentActivity {

    public static String EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE";
    private static int mTotalPrice;
    @Override
    protected Fragment createFragment() {
        return OrderAcceptFragment.newInstance(mTotalPrice);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static Intent createIntent(Activity activity, int totalPrice) {
        mTotalPrice = totalPrice;
        Intent intent = new Intent(activity, OrderAcceptActivity.class);
        intent.putExtra(EXTRA_TOTAL_PRICE, totalPrice);
        return intent;
    }
}
