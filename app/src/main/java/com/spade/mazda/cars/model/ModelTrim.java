package com.spade.mazda.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ModelTrim implements Parcelable {

    @SerializedName("trimName")
    @Expose
    private String trimName;
    @SerializedName("trimId")
    @Expose
    private Integer trimId;
    public final static Parcelable.Creator<ModelTrim> CREATOR = new Creator<ModelTrim>() {
        @SuppressWarnings({"unchecked"})
        public ModelTrim createFromParcel(Parcel in) {
            return new ModelTrim(in);
        }

        public ModelTrim[] newArray(int size) {
            return (new ModelTrim[size]);
        }

    };

    public ModelTrim(Parcel in) {
        this.trimName = ((String) in.readValue((String.class.getClassLoader())));
        this.trimId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ModelTrim() {
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public Integer getTrimId() {
        return trimId;
    }

    public void setTrimId(Integer trimId) {
        this.trimId = trimId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(trimName);
        dest.writeValue(trimId);
    }

    public int describeContents() {
        return 0;
    }

}