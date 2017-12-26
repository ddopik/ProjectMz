package com.spade.mazda.ui.authentication.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarModel;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public interface RegistrationView extends BaseView {
    void navigateToLogin();

    void navigateToNextStep();

//    void showCarModels(List<CarModel> carModels);

    void showLoading();

    void hideLoading();

    void navigateToActivate(String email);

    void setCarModel(String carModel);

    void setCarYear(String carYear);

    void setCarTrim(String carTrim);

    void setCarColor(String carColor);

    void setCarModelError();

    void setCarYearError();

    void setCarTrimError();

    void setCarColorError();

    void setNationalIdError(int resID);

    void setChassisError();

    void setMotorError();
}

