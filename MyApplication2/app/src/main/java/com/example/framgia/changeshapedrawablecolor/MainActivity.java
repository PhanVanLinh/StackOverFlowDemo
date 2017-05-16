package com.example.framgia.changeshapedrawablecolor;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);

        GradientDrawable gradientDrawable = (GradientDrawable)textView.getBackground();
        gradientDrawable.setColor(Color.parseColor("#ff0000"));
        gradientDrawable.setStroke(20, Color.parseColor("#0000ff"));
    }
}
