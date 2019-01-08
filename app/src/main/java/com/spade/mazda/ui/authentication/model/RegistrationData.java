package com.spade.mazda.ui.authentication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/21/17.
 */

public class RegistrationData {
    @SerializedName("msg")
    private String message;
    @SerializedName("code")
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
