package com.spade.mazda.ui.cars.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.model.City;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface TestDriveView extends BaseView {
    void showCarModels(List<CarModel> carModels);

    void showNearestShowRooms(List<Branch> branches);

    void showCities(List<City> cityList);

    void showProgressDialog();

    void hideProgressDialog();

    void showConfirmationDialog();

    void finish();
}
