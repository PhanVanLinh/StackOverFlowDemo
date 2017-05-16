package com.example.framgia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Remember to change library in gradle
 * Mapion use library in libs/MapboxGLAndroidSDK-release.aar
 * Mapbox use library in 'com.mapbox.mapboxsdk:mapbox-android-sdk:5.0.2@aar'
 */
public class SecondActivity extends AppCompatActivity {
    String Mapion_Key = "mt-pk" +
                    ".eyJ1IjoibWFwaW9uIiwiYSI6InJ5ZXFiOWJyZ3J2MjV3ZDcyNXY3Z3dmYzYifQ" +
                    ".XoOA7fe-5nUi8ALSyCl277";

    String Mapbox_Key = "pk.eyJ1IjoicGhhbnZhbmxpbmg5NHZuIiwiYSI6ImNqMW44ZmtlbDAwcjYyd28yaDQzbzJwejAifQ.v9ID5IxcItXpaw72ZVN4dA";


    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), Mapbox_Key);
        setContentView(R.layout.activity_second);

        mapView = (MapView) findViewById(R.id.mapView);
        //TODO need enable 2 line codes below when use mapion key (don't enable -> map black)
//        mapView.setCopyright("© Mapion 地図データ © ZENRIN © 北海道地図");
//        mapView.setStyleUrl("https://www.mapion.co.jp/d/smp-apps/common/mapion-gl-stylels/style-raster-mapion-ssl.json");
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                Toast.makeText(SecondActivity.this, "ready", Toast.LENGTH_SHORT).show();

                mapboxMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(new LatLng(35.50, 139.125), 6));

                mapboxMap.addMarker(new MarkerOptions().position(new LatLng(35.75, 139.125))
                        .title("Marker")
                        .snippet("."));

                mapboxMap.addMarker(new MarkerViewOptions().position(new LatLng(35.25, 139.125))
                        .title("MarkerView")
                        .snippet("."));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
