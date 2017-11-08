package com.spade.mazda.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.cars.view.interfaces.CarDetailsView;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public interface CarDetailsPresenter extends BasePresenter<CarDetailsView> {

    void setUpViewPagerFragments();
}
