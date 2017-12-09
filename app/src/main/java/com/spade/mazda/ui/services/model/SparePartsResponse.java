package com.spade.mazda.ui.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsResponse {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("data")
    private List<SparePartCategory> sparePartCategories;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<SparePartCategory> getSparePartCategories() {
        return sparePartCategories;
    }

    public void setSparePartCategories(List<SparePartCategory> sparePartCategories) {
        this.sparePartCategories = sparePartCategories;
    }
}
