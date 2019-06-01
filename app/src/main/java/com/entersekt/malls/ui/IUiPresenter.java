package com.entersekt.malls.ui;

import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;

import java.util.List;

public interface IUiPresenter {
    void showMalls(List<Mall> malls, String cityName);
    void showCityShops(List<Shop> shops, String cityName);
    void showShops(List<Shop> shops, String mallName);
    void showCities(List<City> city);
}
