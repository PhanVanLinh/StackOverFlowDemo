package com.example.toong.dialogaboveotherdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAlertDialog == null) {
                    createAlertDialog();
                }else{
                    mAlertDialog.show();
                }
            }
        });
    }

    private void createAlertDialog() {
        mAlertDialog = new AlertDialog.Builder(this).
                setTitle("hehe").
                setMessage("hoho")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        final ProgressDialog progressDialog = new ProgressDialog(mAlertDialog.getContext());
                        progressDialog.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.hide();
                                dialog.dismiss();
                            }
                        }, 1000);

//                        dialog.dismiss();
                    }
                }).
                show();
    }
}
