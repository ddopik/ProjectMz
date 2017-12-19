
package com.spade.mazda.ui.mazda_club.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MazdaClubResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private MazdaClubData mazdaClubData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public MazdaClubData getMazdaClubData() {
        return mazdaClubData;
    }

    public void setMazdaClubData(MazdaClubData mazdaClubData) {
        this.mazdaClubData = mazdaClubData;
    }

}
