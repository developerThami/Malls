package com.entersekt.malls.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mall {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("shops")
    private List<Shop> shops;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Shop> getShops() {
        return shops;
    }
}
