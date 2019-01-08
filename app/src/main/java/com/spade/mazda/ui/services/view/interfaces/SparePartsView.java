package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.services.model.SparePartCategory;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public interface SparePartsView extends BaseView {
    void showSparParts(List<SparePartCategory> sparePartCategories);

    void showCarModels(List<CarModel> carModels);

    void showLoading();

    void hideLoading();
}
