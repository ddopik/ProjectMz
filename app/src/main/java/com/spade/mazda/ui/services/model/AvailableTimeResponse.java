package com.spade.mazda.ui.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableTimeResponse {



    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("data")
    @Expose
    public List<AvailableTimeData> data = null;
}
