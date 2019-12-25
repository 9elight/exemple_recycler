package com.delight.weatherapp.ui.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.delight.weatherapp.ForeGroundService;
import com.delight.weatherapp.R;
import com.delight.weatherapp.base.BaseMapActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import butterknife.BindView;

public class MapActivity extends BaseMapActivity  {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab.setOnClickListener(v -> {
            if (flag == false){
                flag = true;
                Intent intent = new Intent(this, ForeGroundService.class);
                startService(intent);
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
            }else{
                Intent intent = new Intent(this, ForeGroundService.class);
                stopService(intent);
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                flag = false;
            }
        });
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

    private void coordService(){

    }

}
