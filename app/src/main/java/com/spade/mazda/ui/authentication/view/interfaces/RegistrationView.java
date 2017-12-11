package com.spade.mazda.ui.authentication.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarModel;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public interface RegistrationView extends BaseView {
    void showCarModels(List<CarModel> carModels);

    void navigateToNextStep();

    void showLoading();

    void hideLoading();

    void navigateToActivate(String email);

    void navigateToLogin();
}
