package com.example.framgia.changeshapedrawablecolor;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by phanvanlinh on 12/05/2017.
 * Email: phanvanlinh.94vn@gmail.com
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        scanBluetooth();
    }

    protected void scanBluetooth(){}
}
