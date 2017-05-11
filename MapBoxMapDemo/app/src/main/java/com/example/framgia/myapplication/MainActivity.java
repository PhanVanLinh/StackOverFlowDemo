package com.example.framgia.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoicGhhbnZhbmxpbmg5NHZuIiwiYSI6ImNqMW44ZmtlbDAwcjYyd28yaDQzbzJwejAifQ.v9ID5IxcItXpaw72ZVN4dA");
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                Toast.makeText(MainActivity.this, "ready", Toast.LENGTH_SHORT).show();
                CameraPosition currentCameraPosition = mapboxMap.getCameraPosition();
                CameraPosition position = new CameraPosition.Builder(currentCameraPosition).target(new LatLng(35, 106)).zoom(8).build();
                mapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position));

                MarkerViewOptions markerViewOptions = new MarkerViewOptions()
                        .position(new LatLng(35, 106));

                MarkerView markerView = mapboxMap.addMarker(markerViewOptions);
                //mapboxMap.selectMarker(markerView); // the trick to make marker view return click at first time

                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {

                        return true;
                    }
                });

//                mapboxMap.getMarkerViewManager().setOnMarkerViewClickListener(new MapboxMap.OnMarkerViewClickListener() {
//                    @Override
//                    public boolean onMarkerClick(@NonNull Marker marker, @NonNull View view, @NonNull MapboxMap.MarkerViewAdapter adapter) {
//                        Log.i("TAG", "MARKER clicked");
//                        return true;
//                    }
//                });
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
