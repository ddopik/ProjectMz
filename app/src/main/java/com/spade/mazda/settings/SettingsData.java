package com.spade.mazda.settings;

import com.spade.mazda.cars.model.CarModel;
import com.spade.mazda.settings.model.Branches;
import com.spade.mazda.settings.model.ShowRoom;
import com.spade.mazda.settings.model.Trim;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

class SettingsData {

    private static SettingsData ourInstance;
    private List<CarModel> carModelList;
    private List<Branches> branchesList;
    private List<ShowRoom> showRoomList;
    private List<Trim> trimList;

    static SettingsData getInstance() {
        if (ourInstance == null) {
            ourInstance = new SettingsData();
        }
        return ourInstance;
    }


    public List<CarModel> getCarModelList() {
        return carModelList;
    }

    public void setCarModelList(List<CarModel> carModelList) {
        this.carModelList = carModelList;
    }

    public List<Branches> getBranchesList() {
        return branchesList;
    }

    public void setBranchesList(List<Branches> branchesList) {
        this.branchesList = branchesList;
    }

    public List<ShowRoom> getShowRoomList() {
        return showRoomList;
    }

    public void setShowRoomList(List<ShowRoom> showRoomList) {
        this.showRoomList = showRoomList;
    }

    public List<Trim> getTrimList() {
        return trimList;
    }

    public void setTrimList(List<Trim> trimList) {
        this.trimList = trimList;
    }
}
