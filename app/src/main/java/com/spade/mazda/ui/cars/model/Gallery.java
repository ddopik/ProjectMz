
package com.spade.mazda.ui.cars.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gallery implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Gallery> CREATOR = new Creator<Gallery>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        public Gallery[] newArray(int size) {
            return (new Gallery[size]);
        }

    };

    protected Gallery(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Gallery() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}
