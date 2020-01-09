package com.delight.weatherapp.data.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.delight.weatherapp.data.NotificationHelper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class ForegroundService extends Service {
    private FusedLocationProviderClient fusedLocationProvider;
    private LocationRequest locationRequest;
    private ArrayList<Location> locations = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, NotificationHelper.createNotification(getApplicationContext()
                ,"locations","body"));
        if (intent.getBooleanExtra("true",false) == true){
            requestLocationUpdates();
        }

        return START_STICKY;

    }
    public void requestLocationUpdates() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)==
                PermissionChecker.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==
                        PermissionChecker.PERMISSION_GRANTED ){
            fusedLocationProvider = new FusedLocationProviderClient(this);
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(2000);
            locationRequest.setInterval(4000);
            fusedLocationProvider.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

                    super.onLocationResult(locationResult);
                    Log.e("------", "lat: " + locationResult.getLastLocation().getLatitude()+
                            "lon: " + locationResult.getLastLocation().getLongitude() + "  " +locations.size());
                            locations.add(locationResult.getLastLocation());



                }
            }, getMainLooper());
        }

    }







}
