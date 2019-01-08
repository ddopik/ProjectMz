
package com.spade.mazda.ui.fabrika.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FabricaResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<FabricaData> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<FabricaData> getData() {
        return data;
    }

    public void setData(List<FabricaData> data) {
        this.data = data;
    }

}
