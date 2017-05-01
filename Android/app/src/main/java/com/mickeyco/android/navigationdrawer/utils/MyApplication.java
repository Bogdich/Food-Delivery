package com.mickeyco.android.navigationdrawer.utils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import io.realm.Realm;

/**
 * Created by gusvl on 20.10.2016.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
