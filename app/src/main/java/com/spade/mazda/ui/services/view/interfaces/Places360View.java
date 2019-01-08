package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.services.model.Place360;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface Places360View extends BaseView {
    void showPlaces(List<Place360> place360List);
}
