package com.spade.mazda.ui.cars.model.realm_models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ayman Abouzeid on 11/20/17.
 */

public class CarYear extends RealmObject {
    @PrimaryKey
    private int yearID;
    private String yearName;
    private RealmList<ModelTrim> modelTrims;

    public int getYearID() {
        return yearID;
    }

    public void setYearID(int yearID) {
        this.yearID = yearID;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public List<ModelTrim> getModelTrims() {
        return modelTrims;
    }

    public void setModelTrims(RealmList<ModelTrim> modelTrims) {
        this.modelTrims = modelTrims;
    }
}
