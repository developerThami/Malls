package com.entersekt.malls;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.entersekt.citylibrary.CityLibrary;
import com.entersekt.malls.listener.CitySelectActionListener;
import com.entersekt.malls.listener.MallSelectActionListener;
import com.entersekt.malls.listener.ShowCityShopsActionListener;


public class MainActivity extends AppCompatActivity implements  ViewPresenter.DisplayFragmentListener, CitySelectActionListener , MallSelectActionListener , ShowCityShopsActionListener {

    private CityLibrary library;
    private ViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = new CityLibrary(this);
        library.syncCityData();

        presenter = new ViewPresenter(this);
        presenter.showCities(library.getCities());
    }

    @Override
    public void onCitySelected(int cityId) {
        presenter.showMalls(library.getMalls(cityId));
    }

    @Override
    public void onMallSelected(int mallId) {
        presenter.showShops(library.getShops(mallId));
    }

    @Override
    public void onShowCityShopsSelected(int cityId) {
        presenter.showCityShops(library.getShopsByCity(cityId));
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}
