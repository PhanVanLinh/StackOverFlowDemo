package com.example.framgia.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
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
    MarkerView markerView1;
    MarkerView markerView3;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoicGhhbnZhbmxpbmg5NHZuIiwiYSI6ImNqMW44ZmtlbDAwcjYyd28yaDQzbzJwejAifQ.v9ID5IxcItXpaw72ZVN4dA");
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn_update_marker);
        s = "0";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer timer = new CountDownTimer(100000, 5) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        for (int i = 0; i < 1000; i++) {
                            double a = Math.floor(Math.random() * (10000 - 1 + 1) + 1) * 10 / 2 + 3 - Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + Math.random();
                            s += a + a;
                            s += markerView3.getAlpha() + markerView1.getPosition().getLongitude() + markerView1.getPosition().getLatitude();

                        }

                        DistanceTextMarkerView distanceTextMarkerView = new DistanceTextMarkerView.ItemMarkerViewBuilder(MainActivity.this).setMessage("jaja").setColor(Color.BLUE).build();

                        distanceTextMarkerView.setMessage("" + markerView1.getPosition().getLatitude());
                        LatLng newPosition;
                        if (millisUntilFinished % 2 == 0) {
                            newPosition = new LatLng(markerView1.getPosition().getLatitude() + 0.01, markerView1.getPosition().getLongitude() + 0.01);
                        } else {
                            newPosition = new LatLng(markerView1.getPosition().getLatitude() - 0.01, markerView1.getPosition().getLongitude() - 0.01);
                        }


                        markerView1.setPosition(newPosition);
                        markerView1.setIcon(IconFactory.getInstance(MainActivity.this).fromBitmap(distanceTextMarkerView.toBitmap()));
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                timer.start();
            }
        });

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

                DistanceTextMarkerView distanceTextMarkerView = new DistanceTextMarkerView.ItemMarkerViewBuilder(MainActivity.this).setMessage("jaja").setColor(Color.BLUE).build();


                DistanceTextMarkerView distanceTextMarkerView2 = new DistanceTextMarkerView.ItemMarkerViewBuilder(MainActivity.this).setMessage("zzzzzzzzzzzzz").setColor(Color.BLUE).build();


                MarkerViewOptions markerViewOptions = new MarkerViewOptions()
                        .position(new LatLng(35, 106)).icon(IconFactory.getInstance(MainActivity.this).fromBitmap(distanceTextMarkerView.toBitmap()));

                markerView1 = mapboxMap.addMarker(markerViewOptions);

                MarkerViewOptions markerViewOptions2 = new MarkerViewOptions()
                        .position(new LatLng(35.25, 106.25)).icon(IconFactory.getInstance(MainActivity.this).fromBitmap(distanceTextMarkerView2.toBitmap()));


                mapboxMap.addMarker(markerViewOptions2);


                MarkerViewOptions markerViewOptions3 = new MarkerViewOptions()
                        .position(new LatLng(34.75, 106.25)).icon(IconFactory.getInstance(MainActivity.this).fromResource(R.mipmap.ic_launcher_round));
//                markerViewOptions3.a
                markerView3 = mapboxMap.addMarker(markerViewOptions3);


                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher_round);
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(34.75, 106)).icon(IconFactory.getInstance(MainActivity.this).fromBitmap(addPaddingRightForBitmap(icon, 10)));
                Marker marker = mapboxMap.addMarker(markerOptions);
                mapboxMap.updateMarker(marker);

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(34.75, 106.125))
                        .title("Hello World!")
                        .snippet("Welcome to my marker."));
            }
        });
    }

    public Bitmap addPaddingRightForBitmap(Bitmap bitmap, int paddingRight) {
        Bitmap outputBitmap = Bitmap.createBitmap(bitmap.getWidth() + paddingRight, bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);
        canvas.drawColor(Color.RED);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return outputBitmap;
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
