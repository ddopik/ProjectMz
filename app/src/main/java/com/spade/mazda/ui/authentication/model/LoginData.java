
package com.spade.mazda.ui.authentication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData implements Parcelable
{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private UserModel user;
    public final static Creator<LoginData> CREATOR = new Creator<LoginData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        public LoginData[] newArray(int size) {
            return (new LoginData[size]);
        }

    }
    ;

    protected LoginData(Parcel in) {
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((UserModel) in.readValue((User.class.getClassLoader())));
    }

    public LoginData() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(token);
        dest.writeValue(user);
    }

    public int describeContents() {
        return  0;
    }

}
