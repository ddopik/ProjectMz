package com.spade.mazda.ui.cars.view.interfaces;

import android.support.v4.app.Fragment;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarYear;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public interface CarDetailsView extends BaseView {

    void showCarDetails();

    void showCarYearsAndTrims(List<CarYear> carYears);

    void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles);

    void updateUI(String modelImage, String Name);

//    void navigateToBook();
}
