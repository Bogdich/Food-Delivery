package com.mickeyco.android.fooddelivery.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.fragments.RegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        if(fragment == null)
        {
            fragment = new RegisterFragment();
            fm.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }


}
