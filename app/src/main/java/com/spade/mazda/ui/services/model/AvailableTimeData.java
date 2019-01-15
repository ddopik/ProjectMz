package com.spade.mazda.ui.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailableTimeData {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;


    public boolean isSelected;


}
