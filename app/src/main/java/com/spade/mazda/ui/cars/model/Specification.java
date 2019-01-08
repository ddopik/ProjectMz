
package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specification implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("specifications")
    @Expose
    private String specifications;
    public final static Creator<Specification> CREATOR = new Creator<Specification>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Specification createFromParcel(Parcel in) {
            return new Specification(in);
        }

        public Specification[] newArray(int size) {
            return (new Specification[size]);
        }

    };

    protected Specification(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.specifications = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Specification() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(specifications);
    }

    public int describeContents() {
        return 0;
    }

}
