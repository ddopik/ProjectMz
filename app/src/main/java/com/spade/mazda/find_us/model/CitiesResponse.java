package com.spade.mazda.find_us.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/5/17.
 */

public class CitiesResponse {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("data")
    private List<City> cityList;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
