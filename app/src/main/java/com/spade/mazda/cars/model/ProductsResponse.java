package com.spade.mazda.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ProductsResponse implements Parcelable {

    @SerializedName("success")
    private boolean isSuccess;

    @SerializedName("data")
    private List<CarModel> carModels;

    public final static Parcelable.Creator<ProductsResponse> CREATOR = new Creator<ProductsResponse>() {
        @SuppressWarnings({"unchecked"})
        public ProductsResponse createFromParcel(Parcel in) {
            return new ProductsResponse(in);
        }

        public ProductsResponse[] newArray(int size) {
            return (new ProductsResponse[size]);
        }

    };

    protected ProductsResponse(Parcel in) {
        this.isSuccess = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.carModels, (ProductsResponse.class.getClassLoader()));
    }

    public ProductsResponse() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeValue(isSuccess);
        dest.writeList(carModels);

    }
}
