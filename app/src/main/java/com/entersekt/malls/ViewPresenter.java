package com.entersekt.malls;

import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;

import java.util.List;

public interface ViewPresenter {
    void showMalls(List<Mall> malls);
    void showCityShops(List<Shop> shops);
    void showShops(List<Shop> shops);
    void showCities(List<City> city);
}
