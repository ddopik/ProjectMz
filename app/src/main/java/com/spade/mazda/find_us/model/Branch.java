
package com.spade.mazda.find_us.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String branchName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("city")
    @Expose
    private City city;
    public final static Creator<Branch> CREATOR = new Creator<Branch>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Branch createFromParcel(Parcel in) {
            return new Branch(in);
        }

        public Branch[] newArray(int size) {
            return (new Branch[size]);
        }

    };

    protected Branch(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this._long = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    public Branch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return branchName;
    }

    public void setName(String name) {
        this.branchName = name;
    }

    public String getBranchAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBranchLng() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public City getBranchCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(branchName);
        dest.writeValue(address);
        dest.writeValue(lat);
        dest.writeValue(_long);
        dest.writeValue(city);
    }

    public int describeContents() {
        return 0;
    }

}
