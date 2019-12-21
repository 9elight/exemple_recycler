package com.delight.weatherapp.ui.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.delight.weatherapp.R;
import com.delight.weatherapp.base.BaseMapActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

public class MapActivity extends BaseMapActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        super.onMapReady(mapboxMap);
        mapboxMap.addOnMapLongClickListener(point -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
            builder.setTitle("Внимание!!!");
            builder.setMessage("Вы выбрали координаты");
            builder.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
            builder.setPositiveButton("Да", (dialog, which) -> {
                Intent intent = new Intent();
                intent.putExtra("Lat",point.getLatitude());
                intent.putExtra("Lng",point.getLongitude());
                setResult(RESULT_OK);
                finish();
            });
            return true;
        });

    }

}
