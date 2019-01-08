package com.spade.mazda.ui.cars.model.realm_models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ModelTrim extends RealmObject {
    @PrimaryKey
    private Integer trimId;
    private String trimName;
    private RealmList<TrimColor> trimColors;

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public Integer getTrimId() {
        return trimId;
    }

    public void setTrimId(Integer trimId) {
        this.trimId = trimId;
    }

    public List<TrimColor> getTrimColors() {
        return trimColors;
    }

    public void setTrimColors(RealmList<TrimColor> trimColors) {
        this.trimColors = trimColors;
    }

}