package com.example.framgia.appcompatthemedemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAlert();
            }
        }, 1000);
    }

    private void showAlert(){

        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("A").setMessage("b").setPositiveButton("OK",null).show();


    }
}
