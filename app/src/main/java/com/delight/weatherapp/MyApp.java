package com.delight.weatherapp;

import android.app.Application;

import com.delight.weatherapp.data.PreferenceHelper;
import com.google.android.gms.location.FusedLocationProviderClient;

public class MyApp extends Application {
    public static FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(this);
        fusedLocationProviderClient = new FusedLocationProviderClient(this);
    }
}
