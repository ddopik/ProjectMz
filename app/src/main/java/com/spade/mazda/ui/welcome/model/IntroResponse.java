
package com.spade.mazda.ui.welcome.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IntroResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<IntroSlide> data = null;
    public final static Creator<IntroResponse> CREATOR = new Creator<IntroResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IntroResponse createFromParcel(Parcel in) {
            return new IntroResponse(in);
        }

        public IntroResponse[] newArray(int size) {
            return (new IntroResponse[size]);
        }

    };

    protected IntroResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (IntroSlide.class.getClassLoader()));
    }

    public IntroResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<IntroSlide> getData() {
        return data;
    }

    public void setData(List<IntroSlide> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
