package com.entersekt.malls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.entersekt.malls.network.model.City;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         new Presenter().getAllCities(response -> {});

    }
}
