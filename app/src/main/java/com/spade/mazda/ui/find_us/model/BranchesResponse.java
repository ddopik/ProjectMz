
package com.spade.mazda.ui.find_us.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchesResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Branch> branchesList = null;
    public final static Creator<BranchesResponse> CREATOR = new Creator<BranchesResponse>() {
        @SuppressWarnings({
                "unchecked"
        })
        public BranchesResponse createFromParcel(Parcel in) {
            return new BranchesResponse(in);
        }

        public BranchesResponse[] newArray(int size) {
            return (new BranchesResponse[size]);
        }

    };

    protected BranchesResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.branchesList, (Branch.class.getClassLoader()));
    }

    public BranchesResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Branch> getBranchesList() {
        return branchesList;
    }

    public void setData(List<Branch> branchesList) {
        this.branchesList = branchesList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(branchesList);
    }

    public int describeContents() {
        return 0;
    }

}
