package com.delight.weatherapp.ui.map;

import androidx.annotation.NonNull;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.delight.weatherapp.R;
import com.delight.weatherapp.base.BaseMapActivity;
import com.delight.weatherapp.data.service.ForegroundService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import butterknife.BindView;


public class MapActivity extends BaseMapActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    private boolean isClicked = false;
    private boolean permissionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callPermissions();
        fab.setOnClickListener(v -> {
            if (isClicked == false) {
                isClicked = true;
                Intent intent = new Intent(this, ForegroundService.class);
                intent.putExtra("true", permissionStatus);
                startService(intent);

                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
            } else {
                Intent intent = new Intent(this, ForegroundService.class);
                stopService(intent);
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                isClicked = false;
            }
        });
    }

    @Subscribe
    public void locationUpdate(ArrayList<Point> locations) {
        drawRoad(locations);

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        super.onMapReady(mapboxMap);
        assert mapboxMap.getLocationComponent().getLastKnownLocation() != null;
        mapboxMap.addOnMapClickListener(point -> {
            Toast.makeText(MapActivity.this, String.format("User clicked at: %s", point.toString()), Toast.LENGTH_LONG).show();
            return true;
        });
        mapboxMap.addOnMapLongClickListener(point -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
            builder.setTitle("Внимание!!!").setMessage("Вы выбрали окончательный координаты?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        Intent intent = new Intent();
                        intent.putExtra("lat", point.getLatitude());
                        intent.putExtra("lng", point.getLongitude());
                        setResult(RESULT_OK, intent);
                        finish();
                    }).setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        });
    }

    public void callPermissions() {
        Permissions.check(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, "Нужен доступ к местоположению",
                new Permissions.Options().setSettingsDialogTitle("Внимание!!!").setRationaleDialogTitle("Доступ к местоположению"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        permissionStatus = true;
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        super.onDenied(context, deniedPermissions);
                        callPermissions();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

