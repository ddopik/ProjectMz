package com.spade.mazda.ui.home.presenter;

import android.location.LocationManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.home.view.HomeView;

/**
 * Created by Ayman Abouzeid on 12/6/17.
 */

public interface HomePresenter extends BasePresenter<HomeView> {

    void getOffers();

    void loadNearByPlaces(double latitude, double longitude);
}
