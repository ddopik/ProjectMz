
package com.spade.mazda.ui.home.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffersResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Offer> data = null;
    public final static Creator<OffersResponse> CREATOR = new Creator<OffersResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OffersResponse createFromParcel(Parcel in) {
            return new OffersResponse(in);
        }

        public OffersResponse[] newArray(int size) {
            return (new OffersResponse[size]);
        }

    };

    protected OffersResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Offer.class.getClassLoader()));
    }

    public OffersResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Offer> getData() {
        return data;
    }

    public void setData(List<Offer> data) {
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
