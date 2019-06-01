package com.entersekt.malls.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.entersekt.citylibrary.CityLibrary;
import com.entersekt.citylibrary.network.model.Mall;
import com.entersekt.citylibrary.network.model.Shop;
import com.entersekt.malls.R;
import com.entersekt.malls.listener.CitySelectActionListener;
import com.entersekt.malls.listener.DisplayFragmentListener;
import com.entersekt.malls.listener.MallSelectActionListener;
import com.entersekt.malls.listener.ShowCityShopsActionListener;
import com.entersekt.malls.ui.UiPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements DisplayFragmentListener,
        CitySelectActionListener, MallSelectActionListener, ShowCityShopsActionListener {

    private CityLibrary library;
    private UiPresenter presenter;

    private int selectedCityId;
    private String selectedCityName;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.showCitiesBtn);
        button.setOnClickListener(v -> onShowCityShopsSelected(selectedCityId, selectedCityName));

        library = new CityLibrary(this);
        library.syncCityData();

        presenter = new UiPresenter(this, this);
        presenter.showCities(library.getCities());

    }

    @Override
    public void onCitySelected(int cityId, String cityName) {

        button.setVisibility(View.VISIBLE);

        this.selectedCityId = cityId;
        this.selectedCityName = cityName;

        List<Mall> malls = library.getMalls(cityId);
        if (malls != null) {
            enableBackNavigation();
            presenter.showMalls(malls, cityName);
        } else {
            String message = String.format("No malls found for %s", cityName);
            presenter.showMessage(message);
        }
    }

    @Override
    public void onMallSelected(int mallId, String mallName) {

        button.setVisibility(View.INVISIBLE);

        List<Shop> shops = library.getShops(mallId);
        if (shops != null) {
            enableBackNavigation();
            presenter.showShops(shops, mallName);
        } else {
            String message = String.format("No shops found for %s", mallName);
            presenter.showMessage(message);
        }
    }

    @Override
    public void onShowCityShopsSelected(int cityId, String cityName) {

        button.setVisibility(View.INVISIBLE);

        List<Shop> shopsByCity = library.getShopsByCity(cityId);
        if (shopsByCity != null) {
            enableBackNavigation();
            presenter.showCityShops(shopsByCity, cityName);
        } else {
            String message = String.format("No shops found for %s", cityName);
            presenter.showMessage(message);
        }
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.getTag()).commit();
    }

    @Override
    public void onBackPressed() {
        executeBackAction();
    }

    private void executeBackAction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();

        if (backStackEntryCount >= 1) {
            if (backStackEntryCount != 1) {
                fragmentManager.popBackStack();
            } else {
                finish();
            }
        }


    }

    private void enableBackNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            executeBackAction();
        }
        return super.onOptionsItemSelected(item);
    }
}
