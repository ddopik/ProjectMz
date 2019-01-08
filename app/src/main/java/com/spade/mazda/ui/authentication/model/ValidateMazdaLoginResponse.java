package com.spade.mazda.ui.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateMazdaLoginResponse {

    @SerializedName("result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String type) {
        this.result = type;
    }
}
