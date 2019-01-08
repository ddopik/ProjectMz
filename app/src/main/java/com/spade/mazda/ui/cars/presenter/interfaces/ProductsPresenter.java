package com.spade.mazda.ui.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.ProductsView;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public interface ProductsPresenter extends BasePresenter<ProductsView> {

    void getProducts(String appLang);

    void openCarDetails(String carId, String carName);
}
