package com.entersekt.malls;

import com.entersekt.malls.network.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements Contract.Presenter {

    private final RetrofitClient retrofitClient = RetrofitClient.start();
    @Override
    public void getAllCities(Contract.CityResponseListener listener) {
        retrofitClient.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onResponse);
    }
}
