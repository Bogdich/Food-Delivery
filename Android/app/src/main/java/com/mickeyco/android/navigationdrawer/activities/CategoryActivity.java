package com.mickeyco.android.navigationdrawer.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.mickeyco.android.navigationdrawer.fragments.CategoryFragment;

public class CategoryActivity extends SingleFragmentActivity
    implements CategoryFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new CategoryFragment();
    }



    @Override
    public void onCategorySelected(int categoryId) {
        Intent intent = OfferListActivity.newIntent(this, categoryId);
        startActivity(intent);
    }
}
