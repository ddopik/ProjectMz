package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.services.model.Location;
import com.spade.mazda.ui.services.model.ServicesLocation;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public interface ServicesLocationsView extends BaseView {
    void showServicesLocations(List<ServicesLocation> servicesLocations);

    void showLocations(List<Location> locations);
}
