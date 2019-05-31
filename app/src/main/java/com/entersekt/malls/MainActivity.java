package com.entersekt.malls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.entersekt.citylibrary.CityLibrary;
import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CityLibrary cityLibrary = new CityLibrary(this);
        cityLibrary.syncCityData();

    }
}
