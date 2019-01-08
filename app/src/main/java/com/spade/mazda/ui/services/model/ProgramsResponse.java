
package com.spade.mazda.ui.services.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramsResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Program> data = null;
    public final static Creator<ProgramsResponse> CREATOR = new Creator<ProgramsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProgramsResponse createFromParcel(Parcel in) {
            return new ProgramsResponse(in);
        }

        public ProgramsResponse[] newArray(int size) {
            return (new ProgramsResponse[size]);
        }

    };

    protected ProgramsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Program.class.getClassLoader()));
    }

    public ProgramsResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Program> getData() {
        return data;
    }

    public void setData(List<Program> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
