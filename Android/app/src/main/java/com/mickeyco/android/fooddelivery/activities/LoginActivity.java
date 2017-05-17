package com.mickeyco.android.fooddelivery.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mickeyco.android.fooddelivery.fragments.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
       return new LoginFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        pref = getSharedPreferences(Constants.PREFS_NAME, 0);
        super.onCreate(savedInstanceState);
    }

}
