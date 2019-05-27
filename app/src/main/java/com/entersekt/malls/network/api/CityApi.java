package com.entersekt.malls.network.api;

import com.entersekt.malls.network.model.CityResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CityApi {

    @GET("/v2/5b7e8bc03000005c0084c210/")
    Single<CityResponse> getCities();
}
