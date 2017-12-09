package com.spade.mazda.ui.find_us.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/5/17.
 */

public class City {

    @SerializedName("id")
    private int cityId;
    @SerializedName("name")
    private String cityName;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
