package com.entersekt.malls;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.entersekt.citylibrary.network.model.City;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;
import com.entersekt.malls.ui.CityListFragment;
import com.entersekt.malls.ui.MallListFragment;
import com.entersekt.malls.ui.ShopListFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPresenter implements IViewPresenter {

    private  DisplayFragmentListener listener;

    public interface DisplayFragmentListener{
        void showFragment(Fragment fragment);
    }

    public ViewPresenter(DisplayFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void showMalls(List<Mall> malls) {
        ArrayList<String> mallNames = getMallNames(malls);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("key", mallNames);

        Fragment fragment = new MallListFragment();
        fragment.setArguments(bundle);
        listener.showFragment(fragment);
    }

    @Override
    public void showCityShops(List<Shop> shops) {

        ArrayList<String> shopNames = getShopNames(shops);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("key", shopNames);

        Fragment fragment = new ShopListFragment();
        fragment.setArguments(bundle);
        listener.showFragment(fragment);
    }

    @Override
    public void showShops(List<Shop> shops) {

        ArrayList<String> shopNames = getShopNames(shops);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("key", shopNames);

        Fragment fragment = new ShopListFragment();
        fragment.setArguments(bundle);
        listener.showFragment(fragment);
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
