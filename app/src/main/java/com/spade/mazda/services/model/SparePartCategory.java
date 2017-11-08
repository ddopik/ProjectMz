package com.spade.mazda.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartCategory {
    @SerializedName("id")
    private int categoryId;

    @SerializedName("name")
    private String categoryName;

    @SerializedName("spare part")
    private List<SparePart> sparePartList;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SparePart> getSparePartList() {
        return sparePartList;
    }

    public void setSparePartList(List<SparePart> sparePartList) {
        this.sparePartList = sparePartList;
    }
}
