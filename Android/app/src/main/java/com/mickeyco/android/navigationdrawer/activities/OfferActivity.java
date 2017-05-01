package com.mickeyco.android.navigationdrawer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mickeyco.android.navigationdrawer.fragments.OfferFragment;

public class OfferActivity extends SingleFragmentActivity {

    private static final String EXTRA_OFFER_ID = "com.mickeyco.android.foodservice.offer_id";
    private int mOfferId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mOfferId = getIntent().getIntExtra(EXTRA_OFFER_ID, 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return OfferFragment.newInstance(mOfferId);
    }


    public static Intent newIntent(Context packageContext, int offerId){
        Intent intent = new Intent(packageContext, OfferActivity.class);
        intent.putExtra(EXTRA_OFFER_ID, offerId);
        return intent;
    }


}
