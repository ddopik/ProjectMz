
package com.spade.mazda.ui.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesLocation implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("start day")
    @Expose
    private String startDay;
    @SerializedName("end day")
    @Expose
    private String endDay;
    @SerializedName("start time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("location")
    @Expose
    private Location location;
    public final static Creator<ServicesLocation> CREATOR = new Creator<ServicesLocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServicesLocation createFromParcel(Parcel in) {
            return new ServicesLocation(in);
        }

        public ServicesLocation[] newArray(int size) {
            return (new ServicesLocation[size]);
        }

    };

    protected ServicesLocation(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.district = ((String) in.readValue((String.class.getClassLoader())));
        this.startDay = ((String) in.readValue((String.class.getClassLoader())));
        this.endDay = ((String) in.readValue((String.class.getClassLoader())));
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
    }

    public ServicesLocation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(district);
        dest.writeValue(startDay);
        dest.writeValue(endDay);
        dest.writeValue(startTime);
        dest.writeValue(endTime);
        dest.writeValue(location);
    }

    public int describeContents() {
        return 0;
    }

}
