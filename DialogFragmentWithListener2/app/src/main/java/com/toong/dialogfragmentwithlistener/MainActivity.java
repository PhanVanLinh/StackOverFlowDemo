package com.toong.dialogfragmentwithlistener;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity onCreate");

        Button btnHello = (Button) findViewById(R.id.btnHello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Dialog dialog = Dialog.newInstance("hihi", new Dialog.DialogListener() {
                    @Override
                    public void onClick() {

                    }
                });
                dialog.show(fm, "");

                //                AlertDialog.Builder alertDialog =
                //                        new AlertDialog.Builder(MainActivity.this).setCancelable(false)
                //                                .setPositiveButton("a", new DialogInterface.OnClickListener() {
                //                                    @Override
                //                                    public void onClick(DialogInterface dialog, int which) {
                //                                        Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT)
                //                                                .show();
                //                                    }
                //                                });
                //
                //                alertDialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity onDestroy");
    }
}
