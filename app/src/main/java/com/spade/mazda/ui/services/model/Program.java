
package com.spade.mazda.ui.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Program implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("benefits")
    @Expose
    private List<InterestRates> interestRatesList;
    public final static Creator<Program> CREATOR = new Creator<Program>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        public Program[] newArray(int size) {
            return (new Program[size]);
        }

    };

    protected Program(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.interestRatesList, (InterestRates.class.getClassLoader()));
    }

    public Program() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InterestRates> getInterestRatesList() {
        return interestRatesList;
    }

    public void setInterestRatesList(List<InterestRates> interestRatesList) {
        this.interestRatesList = interestRatesList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeList(interestRatesList);
    }

    public int describeContents() {
        return 0;
    }

}
