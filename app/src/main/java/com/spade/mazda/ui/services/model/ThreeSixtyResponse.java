
package com.spade.mazda.ui.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThreeSixtyResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Place360> data = null;
    public final static Creator<ThreeSixtyResponse> CREATOR = new Creator<ThreeSixtyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ThreeSixtyResponse createFromParcel(Parcel in) {
            return new ThreeSixtyResponse(in);
        }

        public ThreeSixtyResponse[] newArray(int size) {
            return (new ThreeSixtyResponse[size]);
        }

    };

    protected ThreeSixtyResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Place360.class.getClassLoader()));
    }

    public ThreeSixtyResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Place360> getData() {
        return data;
    }

    public void setData(List<Place360> data) {
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
