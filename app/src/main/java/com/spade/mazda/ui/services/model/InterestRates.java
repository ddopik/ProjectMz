package com.spade.mazda.ui.services.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 12/29/17.
 */

public class InterestRates {

    @SerializedName("year")
    private int yearNumber;
    @SerializedName("value")
    private double interestRate;

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
