package com.spade.mazda.ui.cars.model.realm_models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class CarModel extends RealmObject {

    @PrimaryKey
    private int carModelId;
    private String carModelName;
    private String carModelDescription;
    private String carModelImage;
    private RealmList<CarYear> carYears;


    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getCarModelDescription() {
        return carModelDescription;
    }

    public void setCarModelDescription(String carModelDescription) {
        this.carModelDescription = carModelDescription;
    }

    public String getCarModelImage() {
        return carModelImage;
    }

    public void setCarModelImage(String carModelImage) {
        this.carModelImage = carModelImage;
    }

    public List<CarYear> getCarYears() {
        return carYears;
    }

    public void setCarYears(RealmList<CarYear> carYears) {
        this.carYears = carYears;
    }
}
