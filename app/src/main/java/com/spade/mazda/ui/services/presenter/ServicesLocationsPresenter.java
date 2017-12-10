package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.ServicesLocationsView;

/**
 * Created by Ayman Abouzeid on 12/10/17.
 */

public interface ServicesLocationsPresenter extends BasePresenter<ServicesLocationsView> {
    void getServicesLocation(String type);

    void getLocations();

    void getServiceLocationsByLocationId(int locationID);

    void getAllServiceLocation();
}
