package com.spade.mazda.ui.cars.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface BookCarView extends BaseView {
//    void showCarModels(List<CarModel> carModels);

    void showNearestShowRooms(List<Branch> branches);

    void showName(String userName);

    void showMobileNumber(String mobileNumber);

    void showProgressDialog();

    void hideProgressDialog();

//    void setSelectedCar(CarModel selectedCar);

    void showConfirmationDialog();

    void finish();

    void setCarModel(String carModel);

    void setCarYear(String carYear);

    void setCarTrim(String carTrim);

    void setBranchName(String branchName);

    void setCarModelError();

    void setCarYearError();

    void setCarTrimError();

    void setNameError();

    void setMobileNumberError();

    void setBranchError();
}

