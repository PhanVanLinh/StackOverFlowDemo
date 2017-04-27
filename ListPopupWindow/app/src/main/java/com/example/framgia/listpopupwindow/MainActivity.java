package com.example.framgia.listpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showListPopupWindow(v);
    }
});
    }

private void showListPopupWindow(View anchorView) {
    final ListPopupWindow listPopupWindow = new ListPopupWindow(this);
    listPopupWindow.setWidth(600);
    List<String> sampleData = new ArrayList<>();
    sampleData.add("A");
    sampleData.add("B");
    sampleData.add("CCCCCCCCCCCCCC");
    sampleData.add("D");
    sampleData.add("EEEEEEEEE");

    listPopupWindow.setAnchorView(anchorView);
    ListPopupWindowAdapter listPopupWindowAdapter = new ListPopupWindowAdapter(this, sampleData, new ListPopupWindowAdapter.OnClickDeleteButtonListener() {
        @Override
        public void onClickDeleteButton(int position) {
            Toast.makeText(MainActivity.this, "Click delete " + position, Toast.LENGTH_SHORT).show();
            listPopupWindow.dismiss();
        }
    });
    listPopupWindow.setAdapter(listPopupWindowAdapter);
    listPopupWindow.show();
}
}
