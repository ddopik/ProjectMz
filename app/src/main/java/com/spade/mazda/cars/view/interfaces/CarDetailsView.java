package com.spade.mazda.cars.view.interfaces;

import android.support.v4.app.Fragment;

import com.spade.mazda.base.BaseView;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public interface CarDetailsView extends BaseView {

    void showCarDetails();

    void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles);
}
