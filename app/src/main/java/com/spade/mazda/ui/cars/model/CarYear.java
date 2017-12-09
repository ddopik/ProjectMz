package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/20/17.
 */

public class CarYear implements Parcelable {

    @SerializedName("id")
    private int yearID;
    @SerializedName("name")
    private String yearName;
    @SerializedName("trims")
    private List<ModelTrim> modelTrims;

    public final static Parcelable.Creator<CarYear> CREATOR = new Creator<CarYear>() {
        @SuppressWarnings({"unchecked"})
        public CarYear createFromParcel(Parcel in) {
            return new CarYear(in);
        }

        public CarYear[] newArray(int size) {
            return (new CarYear[size]);
        }

    };

    public CarYear(Parcel in) {
        this.yearName = ((String) in.readValue((String.class.getClassLoader())));
        this.yearID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.modelTrims, (ModelTrim.class.getClassLoader()));

    }

    public CarYear() {
    }

    public int getYearID() {
        return yearID;
    }

    public void setYearID(int yearID) {
        this.yearID = yearID;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(yearID);
        parcel.writeString(yearName);
        parcel.writeList(modelTrims);
    }
}
