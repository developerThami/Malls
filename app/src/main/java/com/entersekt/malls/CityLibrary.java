package com.entersekt.malls;

import android.content.Context;

import com.entersekt.malls.network.RetrofitCityClient;
import com.entersekt.malls.network.model.City;
import com.entersekt.malls.network.model.CityResponse;
import com.entersekt.malls.network.model.Mall;
import com.entersekt.malls.network.model.Shop;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CityLibrary implements Contract.CityLibrary {

    private static RetrofitCityClient retrofitCityClient;
    private List<City> cities;

    public CityLibrary(Context context) {
        retrofitCityClient = RetrofitCityClient.getInstance(context);
        fetchAllCities();
    }

    private void fetchAllCities() {
        retrofitCityClient.fetchRemoteCityData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setCities);
    }

    private void setCities(CityResponse response) {
        this.cities = response.getCities();
    }

    @Override
    public List<City> getCities() {
        return this.cities;
    }

    @Override
    public City getCity(int cityId) {
        for (City city : cities) {
            if (city.getId() == cityId) {
                return city;
            }
        }
        return null;
    }

    @Override
    public List<Mall> getMalls(int cityId) {
        for (City city : cities) {
            if (city.getId() == cityId) {
                return city.getMalls();
            }
        }
        return null;
    }

    @Override
    public Mall getMall(int mallId) {
        for (City city : cities) {
            for (Mall mall : city.getMalls()) {
                if (mall.getId() == mallId) {
                    return mall;
                }
            }
        }
        return null;
    }

    @Override
    public List<Shop> getShops(int mallId) {
        for (City city : cities) {
            for (Mall mall : city.getMalls()) {
                if (mall.getId() == mallId) {
                    return mall.getShops();
                }
            }
        }
        return null;
    }

    @Override
    public Shop getShop(int shopId) {
        for (City city : cities) {
            for (Mall mall : city.getMalls()) {
                for (Shop shop : mall.getShops()) {
                    if (shop.getId() == shopId) {
                        return shop;
                    }
                }
            }
        }
        return null;
    }
}
