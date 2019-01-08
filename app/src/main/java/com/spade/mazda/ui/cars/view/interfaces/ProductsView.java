package com.spade.mazda.ui.cars.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarModel;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public interface ProductsView extends BaseView {
    void showLoading();

    void hideLoading();

    void showProducts(List<CarModel> carModels);
}
