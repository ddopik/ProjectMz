package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ModelTrim implements Parcelable {

    @SerializedName("name")
    @Expose
    private String trimName;
    @SerializedName("id")
    @Expose
    private Integer trimId;
    @SerializedName("price")
    @Expose
    private String trimPrice;
    @SerializedName("colors")
    private List<TrimColor> trimColors;

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
        this.trimPrice = ((String) in.readValue((String.class.getClassLoader())));

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

    public List<TrimColor> getTrimColors() {
        return trimColors;
    }

    public void setTrimColors(List<TrimColor> trimColors) {
        this.trimColors = trimColors;
    }

    public String getTrimPrice() {
        return trimPrice;
    }

    public void setTrimPrice(String trimPrice) {
        this.trimPrice = trimPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(trimName);
        dest.writeValue(trimId);
        dest.writeValue(trimPrice);
        dest.writeList(trimColors);
    }

    public int describeContents() {
        return 0;
    }

}