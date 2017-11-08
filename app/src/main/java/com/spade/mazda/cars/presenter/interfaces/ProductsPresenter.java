package com.spade.mazda.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.cars.view.interfaces.ProductsView;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public interface ProductsPresenter extends BasePresenter<ProductsView> {

    void getProducts(String appLang);
}
