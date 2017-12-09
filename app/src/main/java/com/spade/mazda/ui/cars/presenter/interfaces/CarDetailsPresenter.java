package com.spade.mazda.ui.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.view.interfaces.CarDetailsView;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public interface CarDetailsPresenter extends BasePresenter<CarDetailsView> {

    void getCarModel(int carID);

    void setUpViewPagerFragments();

    void getCarTrims(CarYear carYear);

    void getCarDetails(String appLang, String carId, String trimId);
}
