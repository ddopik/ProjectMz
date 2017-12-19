
package com.spade.mazda.ui.mazda_club.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MazdaClubData {

    @SerializedName("goldenTier")
    @Expose
    private GoldenTier goldenTier;
    @SerializedName("silverTier")
    @Expose
    private SilverTier silverTier;
    @SerializedName("blueTier")
    @Expose
    private BlueTier blueTier;
    @SerializedName("blackTier")
    @Expose
    private BlackTier blackTier;

    public GoldenTier getGoldenTier() {
        return goldenTier;
    }

    public void setGoldenTier(GoldenTier goldenTier) {
        this.goldenTier = goldenTier;
    }

    public SilverTier getSilverTier() {
        return silverTier;
    }

    public void setSilverTier(SilverTier silverTier) {
        this.silverTier = silverTier;
    }

    public BlueTier getBlueTier() {
        return blueTier;
    }

    public void setBlueTier(BlueTier blueTier) {
        this.blueTier = blueTier;
    }

    public BlackTier getBlackTier() {
        return blackTier;
    }

    public void setBlackTier(BlackTier blackTier) {
        this.blackTier = blackTier;
    }

}
