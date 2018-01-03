package com.spade.mazda.ui.profile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 12/28/17.
 */

public class History {

    @SerializedName("comment")
    private String historyComment;
    private String historyDate;
    @SerializedName("image")
    private String historyImage;


    public String getHistoryComment() {
        return historyComment;
    }

    public void setHistoryComment(String historyComment) {
        this.historyComment = historyComment;
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }

    public String getHistoryImage() {
        return historyImage;
    }

    public void setHistoryImage(String historyImage) {
        this.historyImage = historyImage;
    }
}
