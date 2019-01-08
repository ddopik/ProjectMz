package com.spade.mazda.ui.authentication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/21/17.
 */

public class RegistrationResponse {
    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("data")
    private RegistrationData registrationData;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public RegistrationData getRegistrationData() {
        return registrationData;
    }

    public void setRegistrationData(RegistrationData registrationData) {
        this.registrationData = registrationData;
    }
}
