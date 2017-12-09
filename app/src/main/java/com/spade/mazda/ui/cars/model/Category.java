
package com.spade.mazda.ui.cars.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("specifications")
    @Expose
    private List<Specification> specifications = null;
    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    };

    protected Category(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.specifications, (com.spade.mazda.ui.cars.model.Specification.class.getClassLoader()));
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeList(specifications);
    }

    public int describeContents() {
        return 0;
    }

}
