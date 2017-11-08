package com.spade.mazda.services.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePart {


    @SerializedName("id")
    private int sparePartId;
    @SerializedName("title")
    private String sparePartTitle;
    @SerializedName("description")
    private String sparePartDescription;
    @SerializedName("year")
    private String sparePartYear;
    @SerializedName("trim")
    private String SparePartTrim;
    @SerializedName("car")
    private String sparePartCar;

    public int getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(int sparePartId) {
        this.sparePartId = sparePartId;
    }

    public String getSparePartTitle() {
        return sparePartTitle;
    }

    public void setSparePartTitle(String sparePartTitle) {
        this.sparePartTitle = sparePartTitle;
    }

    public String getSparePartDescription() {
        return sparePartDescription;
    }

    public void setSparePartDescription(String sparePartDescription) {
        this.sparePartDescription = sparePartDescription;
    }

    public String getSparePartYear() {
        return sparePartYear;
    }

    public void setSparePartYear(String sparePartYear) {
        this.sparePartYear = sparePartYear;
    }

    public String getSparePartTrim() {
        return SparePartTrim;
    }

    public void setSparePartTrim(String sparePartTrim) {
        SparePartTrim = sparePartTrim;
    }

    public String getSparePartCar() {
        return sparePartCar;
    }

    public void setSparePartCar(String sparePartCar) {
        this.sparePartCar = sparePartCar;
    }
}
