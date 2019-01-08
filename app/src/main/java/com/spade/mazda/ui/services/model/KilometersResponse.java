
package com.spade.mazda.ui.services.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KilometersResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Kilometer> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Kilometer> getData() {
        return data;
    }

    public void setData(List<Kilometer> data) {
        this.data = data;
    }

}
