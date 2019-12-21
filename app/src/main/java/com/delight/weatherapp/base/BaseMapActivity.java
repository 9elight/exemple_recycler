package com.delight.weatherapp.base;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.delight.weatherapp.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import butterknife.BindView;

import static com.delight.weatherapp.BuildConfig.MAP_KEY;

public abstract class BaseMapActivity extends BaseActivity implements OnMapReadyCallback {

    protected MapboxMap map;
    @BindView(R.id.mapView)
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Mapbox.getInstance(this, MAP_KEY);
        super.onCreate(savedInstanceState);
        initMap();
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.SATELLITE_STREETS, style -> map = mapboxMap);

    }

    private void initMap() {
        mapView.getMapAsync(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}
