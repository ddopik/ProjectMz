package com.spade.mazda.ui.home.view;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.home.model.Offer;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public interface HomeView extends BaseView {
    void showLoading();

    void hideLoading();

    void showOffers(List<Offer> offerList);

    GoogleMap getMapView();

    void onLocationChanged(Location location);

    void setMyLocation();

    void showNearByLocations(double latitude, double longitude);
}
