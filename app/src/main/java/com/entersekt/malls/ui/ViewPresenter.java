package com.entersekt.malls.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;
import com.entersekt.malls.listener.DisplayFragmentListener;
import com.entersekt.malls.ui.fragment.CityListFragment;
import com.entersekt.malls.ui.fragment.MallListFragment;
import com.entersekt.malls.ui.fragment.ShopListFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPresenter implements IViewPresenter {

    private DisplayFragmentListener listener;

    public ViewPresenter(DisplayFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void showMalls(List<Mall> malls, String cityName) {

        if (malls != null) {
            ArrayList<String> mallNames = getMallNames(malls);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("key", mallNames);
            bundle.putString(MallListFragment.CITY_NAME,cityName);

            Fragment fragment = new MallListFragment();
            fragment.setArguments(bundle);
            listener.showFragment(fragment);
        }
    }

    @Override
    public void showCityShops(List<Shop> shops, String cityName) {

        if (shops != null) {
            ArrayList<String> shopNames = getShopNames(shops);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("key", shopNames);
            bundle.putString(ShopListFragment.CITY_NAME,cityName);

            Fragment fragment = new ShopListFragment();
            fragment.setArguments(bundle);
            listener.showFragment(fragment);
        }
    }

    @Override
    public void showShops(List<Shop> shops, String mallName) {

        if (shops != null) {
            ArrayList<String> shopNames = getShopNames(shops);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("key", shopNames);
            bundle.putString(ShopListFragment.MALL_NAME,mallName);

            Fragment fragment = new ShopListFragment();
            fragment.setArguments(bundle);
            listener.showFragment(fragment);
        }
    }

    @Override
    public void showCities(List<City> cities) {

        ArrayList<String> cityNames = getCityNames(cities);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("key", cityNames);

        Fragment fragment = new CityListFragment();
        fragment.setArguments(bundle);
        listener.showFragment(fragment);
    }

    private ArrayList<String> getCityNames(List<City> cities) {
        ArrayList<String> items = new ArrayList<>();
        for (City city : cities) {
            items.add(city.getName());
        }
        return items;
    }

    private ArrayList<String> getMallNames(List<Mall> malls) {
        ArrayList<String> items = new ArrayList<>();
        for (Mall mall : malls) {
            items.add(mall.getName());
        }
        return items;
    }

    private ArrayList<String> getShopNames(List<Shop> shops) {
        ArrayList<String> items = new ArrayList<>();
        for (Shop shop : shops) {
            items.add(shop.getName());
        }
        return items;
    }
}
