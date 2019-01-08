
package com.spade.mazda.ui.authentication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private LoginData loginData;
    public final static Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        public LoginResponse[] newArray(int size) {
            return (new LoginResponse[size]);
        }

    };

    protected LoginResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.loginData = ((LoginData) in.readValue((LoginData.class.getClassLoader())));
    }

    public LoginResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(loginData);
    }

    public int describeContents() {
        return 0;
    }

}
