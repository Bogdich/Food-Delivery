package com.mickeyco.android.fooddelivery.utils;

import android.app.Application;

/**
 * Created by gusvl on 20.10.2016.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CartDishes.getInstance();
    }
}
