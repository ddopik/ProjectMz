
package com.spade.mazda.ui.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesLocationsResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ServicesLocation> data = null;
    public final static Creator<ServicesLocationsResponse> CREATOR = new Creator<ServicesLocationsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServicesLocationsResponse createFromParcel(Parcel in) {
            return new ServicesLocationsResponse(in);
        }

        public ServicesLocationsResponse[] newArray(int size) {
            return (new ServicesLocationsResponse[size]);
        }

    };

    protected ServicesLocationsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (ServicesLocation.class.getClassLoader()));
    }

    public ServicesLocationsResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ServicesLocation> getData() {
        return data;
    }

    public void setData(List<ServicesLocation> data) {
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
