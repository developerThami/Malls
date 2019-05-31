package com.entersekt.malls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.entersekt.malls.network.model.City;
import com.entersekt.malls.network.model.Mall;
import com.entersekt.malls.network.model.Shop;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CityLibrary cityLibrary = new CityLibrary(this);

        cityLibrary.syncCityData();

        List<City> cities = cityLibrary.getCities();
        List<Mall> malls = cityLibrary.getMalls(1);
        List<Shop> shops = cityLibrary.getShops(1);


    }
}
