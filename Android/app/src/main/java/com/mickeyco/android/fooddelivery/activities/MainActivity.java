package com.mickeyco.android.fooddelivery.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mickeyco.android.fooddelivery.fragments.RegisterFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected android.support.v4.app.Fragment createFragment() {
        Fragment fragment = null;
//        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
//            if(pref.getBoolean(Constants.IS_DOCTOR,false))
//                fragment = new DoctorFragment();
//            else
//                fragment = new UserFragment();
//        }else {
//            fragment = new LoginFragment();
//        }
        fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        pref = getSharedPreferences(Constants.PREFS_NAME, 0);
        super.onCreate(savedInstanceState);
    }

}
