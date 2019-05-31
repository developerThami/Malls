package com.entersekt.malls;

import com.entersekt.malls.network.model.City;
import com.entersekt.malls.network.model.Mall;
import com.entersekt.malls.network.model.Shop;

import java.util.List;

interface ICityLibrary {

    void syncCityData();

    List<City> getCities();

    City getCity(int cityId);

    List<Mall> getMalls(int cityId);

    Mall getMall(int mallId);

    List<Shop> getShops(int mallId);

    List<Shop> getShopsByCity(int cityId);

    Shop getShop(int shopId);

}

