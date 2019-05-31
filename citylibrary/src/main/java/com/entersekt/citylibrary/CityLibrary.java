package com.entersekt.citylibrary;

import android.content.Context;

import com.entersekt.citylibrary.network.RetrofitCityClient;
import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.CityResponse;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class CityLibrary implements ICityLibrary {

    private static RetrofitCityClient retrofitCityClient;
    private List<City> cities;

    public CityLibrary(Context context) {
        retrofitCityClient = RetrofitCityClient.getInstance(context);
    }

    private void setCities(CityResponse response) {
        this.cities = response.getCities();
    }

    @Override
    public void syncCityData() {
        CityResponse cityResponse = retrofitCityClient.fetchRemoteCityData()
                .subscribeOn(Schedulers.io())
                .blockingGet();
        setCities(cityResponse);
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
    public List<Shop> getShopsByCity(int cityId) {
        List<Shop> shops = new ArrayList<>();
        for (City city : cities) {
            if (city.getId() == cityId) {
                for (Mall mall : city.getMalls()) {
                  shops.addAll(mall.getShops());
                }
            }
        }
        return shops;
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
