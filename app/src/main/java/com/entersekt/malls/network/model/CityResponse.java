package com.entersekt.malls.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("cities")
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }
}
