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

    @Override
    public int hashCode() {
        return cityId * cityName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof City)) return false;

        return this.cityId == ((City) other).cityId;
    }
}
