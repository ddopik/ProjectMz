package com.spade.mazda.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class CarModel implements Parcelable {

    @SerializedName("id")
    private int carModelId;
    @SerializedName("name")
    private String carModelName;
    @SerializedName("description")
    private String carModelDescription;
    @SerializedName("image")
    private String carModelImage;
    @SerializedName("trims")
    private List<ModelTrim> modelTrims;

    public final static Parcelable.Creator<CarModel> CREATOR = new Parcelable.Creator<CarModel>() {
        @SuppressWarnings({"unchecked"})
        public CarModel createFromParcel(Parcel in) {
            return new CarModel(in);
        }

        public CarModel[] newArray(int size) {
            return (new CarModel[size]);
        }

    };

    protected CarModel(Parcel in) {
        this.carModelId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.carModelName = ((String) in.readValue((String.class.getClassLoader())));
        this.carModelDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.carModelImage = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.modelTrims, (ModelTrim.class.getClassLoader()));
    }

    public CarModel() {
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getCarModelDescription() {
        return carModelDescription;
    }

    public void setCarModelDescription(String carModelDescription) {
        this.carModelDescription = carModelDescription;
    }

    public String getCarModelImage() {
        return carModelImage;
    }

    public void setCarModelImage(String carModelImage) {
        this.carModelImage = carModelImage;
    }

    public List<ModelTrim> getModelTrims() {
        return modelTrims;
    }

    public void setModelTrims(List<ModelTrim> modelTrims) {
        this.modelTrims = modelTrims;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeValue(carModelId);
        dest.writeValue(carModelName);
        dest.writeValue(carModelDescription);
        dest.writeValue(carModelImage);
        dest.writeList(modelTrims);
    }
}
