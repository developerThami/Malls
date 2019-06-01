package com.entersekt.malls;

import com.entersekt.citylibrary.CityLibrary;
import com.entersekt.malls.listener.CitySelectActionListener;
import com.entersekt.malls.listener.MallSelectActionListener;
import com.entersekt.malls.listener.ShowCityShopsActionListener;

public class UserAction implements CitySelectActionListener, MallSelectActionListener, ShowCityShopsActionListener {

    private ViewPresenter presenter;
    private CityLibrary library;

    public UserAction(ViewPresenter presenter, CityLibrary library) {
        this.presenter = presenter;
        this.library = library;
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
}
