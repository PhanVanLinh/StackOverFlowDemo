package com.example.framgia.checkboxinrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ItemAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }


    private void prepareMovieData() {
        Item movie = new Item("Mad Max");
        movieList.add(movie);

        Item movie2 = new Item("Mad Max");
        movieList.add(movie2);

        Item movie3 = new Item("Mad Max");
        movieList.add(movie3);

        Item movie4 = new Item("Mad Max");
        movieList.add(movie4);

        Item movie5 = new Item("Mad Max");
        movieList.add(movie5);

        Item movie6 = new Item("Mad Max");
        movieList.add(movie6);

        Item movie7 = new Item("Mad Max");
        movieList.add(movie7);

        Item movie8 = new Item("Mad Max");
        movieList.add(movie8);

        Item movie9 = new Item("Mad Max");
        movieList.add(movie9);

        Item movie10 = new Item("Mad Max");
        movieList.add(movie10);

        mAdapter.notifyDataSetChanged();
    }
}
