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
    void showProgressDialog();

    void hideProgressDialog();

    void showConfirmationDialog();

    void setCarModel(String carModel);

    void setBranchName(String branchName);

    void setDate(String date);

    void setCity(String city);

    void setCarModelError();

    void setBranchError();

    void setDateError();

    void setCityError();

    void finish();
}
