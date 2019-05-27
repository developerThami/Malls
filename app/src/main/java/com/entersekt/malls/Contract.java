package com.entersekt.malls;

import com.entersekt.malls.network.model.CityResponse;

public interface Contract {

    interface Presenter {
        void getAllCities(CityResponseListener listener);
    }

    interface CityResponseListener {
        void onResponse(CityResponse response);
    }
}
