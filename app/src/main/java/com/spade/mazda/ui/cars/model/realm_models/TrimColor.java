package com.spade.mazda.ui.cars.model.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ayman Abouzeid on 11/20/17.
 */

public class TrimColor extends RealmObject {

    @PrimaryKey
    private int colorID;
    private String colorName;

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
