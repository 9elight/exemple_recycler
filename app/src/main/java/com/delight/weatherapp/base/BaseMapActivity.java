package com.delight.weatherapp.base;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.delight.weatherapp.R;
import com.delight.weatherapp.ui.main.MainActivity;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.GeometryCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.gson.GeometryGeoJson;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;

import butterknife.BindView;

import static com.delight.weatherapp.BuildConfig.MAP_KEY;

public abstract class BaseMapActivity extends BaseActivity implements OnMapReadyCallback {
    private final String DIRECTIONS = "DIRECTIONS_LAYER";
    private final String DIRECTIONS_SOURCE = "DIRECTIONS_SOURCE";

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
    private void initSources(){
        map.getStyle().addSource(new GeoJsonSource(DIRECTIONS_SOURCE));
        map.getStyle().addLayer(new LineLayer(DIRECTIONS,DIRECTIONS_SOURCE)
        .withProperties(PropertyFactory.lineWidth(12f),
                PropertyFactory.lineColor(Color.parseColor("#673AB7"))

        ));

    }

    protected void drawRoad(ArrayList<Point> points){
        Feature feature = Feature.fromGeometry(LineString.fromLngLats(points));
        ((GeoJsonSource) map.getStyle().getSourceAs(DIRECTIONS_SOURCE)).setGeoJson(feature);
        Log.e("event", "drawRoad: ");
    }
    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.SATELLITE_STREETS, style -> {
            map = mapboxMap;
            BaseMapActivity.this.initSources();
        });


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
