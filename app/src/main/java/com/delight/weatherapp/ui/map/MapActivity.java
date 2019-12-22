package com.delight.weatherapp.ui.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        mapboxMap.addOnMapClickListener(point -> {

            Toast.makeText(MapActivity.this, String.format("User clicked at: %s", point.toString()), Toast.LENGTH_LONG).show();

            return true;
        });
        mapboxMap.addOnMapLongClickListener(point -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
            builder.setTitle("Внимание!!!").setMessage("Вы выбрали окончательный координаты?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        Intent intent = new Intent();
                        intent.putExtra("lat",point.getLatitude());
                        intent.putExtra("lng",point.getLongitude());
                        setResult(RESULT_OK,intent);
                        finish();

                    }).setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        });


    }

}
