package com.spade.mazda.cars.presenter;

import android.content.Context;

import com.spade.mazda.cars.presenter.interfaces.ProductsPresenter;
import com.spade.mazda.cars.view.interfaces.ProductsView;
import com.spade.mazda.network.ApiHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ProductsPresenterImpl implements ProductsPresenter {

    private ProductsView productsView;
    private Context mContext;

    public ProductsPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void setView(ProductsView view) {
        this.productsView = view;
    }

    @Override
    public void getProducts(String appLang) {
        productsView.showLoading();
        ApiHelper.getCarModels(appLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productsResponse -> {
                    productsView.hideLoading();
                    if (productsResponse != null && productsResponse.getCarModels() != null) {
                        productsView.showProducts(productsResponse.getCarModels());
                    }
                }, throwable -> {
                    productsView.hideLoading();
                });

    }
}
