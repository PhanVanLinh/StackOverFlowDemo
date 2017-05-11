package com.example.framgia.tooltipbypopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.framgia.tooltipbypopupwindow.tooltip.TooltipView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.showTooltip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipView tooltipView = new TooltipView(MainActivity.this);
                tooltipView.showToolTipAbove(v, "hello");
            }
        });
    }
}
