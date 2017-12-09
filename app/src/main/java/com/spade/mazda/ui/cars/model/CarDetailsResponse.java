
package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetailsResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private CarDetailsData carDetailsData;
    public final static Creator<CarDetailsResponse> CREATOR = new Creator<CarDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CarDetailsResponse createFromParcel(Parcel in) {
            return new CarDetailsResponse(in);
        }

        public CarDetailsResponse[] newArray(int size) {
            return (new CarDetailsResponse[size]);
        }

    };

    protected CarDetailsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.carDetailsData = ((CarDetailsData) in.readValue((CarDetailsData.class.getClassLoader())));
    }

    public CarDetailsResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CarDetailsData getCarDetailsData() {
        return carDetailsData;
    }

    public void setCarDetailsData(CarDetailsData carDetailsData) {
        this.carDetailsData = carDetailsData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(carDetailsData);
    }

    public int describeContents() {
        return 0;
    }

}
