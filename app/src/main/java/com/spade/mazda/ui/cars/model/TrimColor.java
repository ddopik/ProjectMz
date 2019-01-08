package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/20/17.
 */

public class TrimColor implements Parcelable {

    @SerializedName("id")
    private int colorID;
    @SerializedName("name")
    private String colorName;


    public final static Parcelable.Creator<TrimColor> CREATOR = new Creator<TrimColor>() {
        @SuppressWarnings({"unchecked"})
        public TrimColor createFromParcel(Parcel in) {
            return new TrimColor(in);
        }

        public TrimColor[] newArray(int size) {
            return (new TrimColor[size]);
        }

    };

    public TrimColor(Parcel in) {
        this.colorName = ((String) in.readValue((String.class.getClassLoader())));
        this.colorID = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public TrimColor() {
    }


    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
