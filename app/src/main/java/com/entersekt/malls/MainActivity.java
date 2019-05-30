package com.entersekt.malls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.entersekt.malls.network.model.City;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CityLibrary cityLibrary = new CityLibrary(this);
        List<City> cities = cityLibrary.getCities();

    }
}
