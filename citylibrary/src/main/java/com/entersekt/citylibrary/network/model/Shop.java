package com.entersekt.citylibrary.network.model;

import com.google.gson.annotations.SerializedName;

public class Shop {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
