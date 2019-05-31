package com.entersekt.citylibrary.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("malls")
    public List<Mall> malls;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Mall> getMalls() {
        return malls;
    }
}
