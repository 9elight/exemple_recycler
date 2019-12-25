package com.delight.weatherapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


import com.delight.weatherapp.data.NotificationHelper;
import com.delight.weatherapp.ui.map.MapActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class ForeGroundService extends Service {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, NotificationHelper.createNotification(this, "title", "body"));
        callPermissions();
        return START_STICKY;
    }

    private void requestLocationUpdate() {
        fusedLocationProviderClient = new FusedLocationProviderClient(this);
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(2000);
        locationRequest.setInterval(4000);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
            }
        }, getMainLooper());

    }

    private void callPermissions() {
        Permissions.check(getApplicationContext(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, "Нужен доступ к местоположению",
                new Permissions.Options().setSettingsDialogTitle("Внимание!!!").setRationaleDialogTitle("Доступ к местоположению"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        requestLocationUpdate();
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        super.onDenied(context, deniedPermissions);
                        callPermissions();
                    }
                });
    }
//    private void callPermissions(){
//
//        Permissions.check(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
//        , "Location permission needed to run this function"
//                , new Permissions.Options().setSettingsDialogTitle("Warning").setRationaleDialogTitle("info")
//                , new PermissionHandler() {
//            @Override
//            public void onGranted() {
//                requestLocationUpdate();
//            }
//
//            @Override
//            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
//                super.onDenied(context, deniedPermissions);
//                callPermissions();
//            }
//                });
//    }
}
