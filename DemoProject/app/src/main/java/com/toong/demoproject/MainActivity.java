package com.toong.demoproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int[] intArray = { 1, 2, 3, 4, 5, 6, 7 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(Arrays.toString(intArray));


    }

    int find(int number){

        int startCheckIndex = 0;
        int endCheckIndex = intArray.length;

        while (startCheckIndex < endCheckIndex){
            if(startCheckIndex)
        }
    }
}
