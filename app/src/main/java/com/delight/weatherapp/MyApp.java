package com.delight.weatherapp;

import android.app.Application;

import com.delight.weatherapp.data.PreferenceHelper;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(this);
    }
}
