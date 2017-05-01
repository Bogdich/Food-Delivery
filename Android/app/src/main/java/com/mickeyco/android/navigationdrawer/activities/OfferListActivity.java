package com.mickeyco.android.navigationdrawer.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mickeyco.android.navigationdrawer.fragments.OfferListFragment;

public class OfferListActivity extends SingleFragmentActivity
    implements OfferListFragment.Callbacks{
    private static final String EXTRA_CATEGORY_ID = "com.mickeyco.android.foodservice.category_id";
    private int mCategoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {
        return OfferListFragment.newInstance(mCategoryId);
    }

    public static Intent newIntent(Context packageContext, int categoryId){
        Intent intent = new Intent(packageContext, OfferListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    public void onOfferSelected(int offerId) {
        Intent intent = OfferActivity.newIntent(this, offerId);
        startActivity(intent);
    }
}
