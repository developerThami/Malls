package com.entersekt.citylibrary.network.api;


import com.entersekt.citylibrary.network.model.CityResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CityApi {

    @GET("/v2/5b7e8bc03000005c0084c210/")
    Single<CityResponse> getCities();
}
