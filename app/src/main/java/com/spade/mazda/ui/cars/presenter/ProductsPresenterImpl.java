package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.content.Intent;

import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.cars.presenter.interfaces.ProductsPresenter;
import com.spade.mazda.ui.cars.view.activity.CarDetailsActivity;
import com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment;
import com.spade.mazda.ui.cars.view.interfaces.ProductsView;

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
        DataSource dataSource = DataSource.getInstance();
        productsView.showProducts(dataSource.getCarModelList());
//        productsView.showLoading();
//        ApiHelper.getCarModels(appLang)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(productsResponse -> {
//                    productsView.hideLoading();
//                    if (productsResponse != null && productsResponse.getCarModels() != null) {
//                        productsView.showProducts(productsResponse.getCarModels());
//                    }
//                }, throwable -> {
//                    productsView.hideLoading();
//                });

    }

    @Override
    public void openCarDetails(String carId,String carName) {
        Intent intent = CarDetailsActivity.getLaunchIntent(mContext);
        intent.putExtra(CarDetailsFragment.EXTRA_CAR_ID, carId);
        intent.putExtra(CarDetailsActivity.EXTRA_CAR_NAME,carName);
        mContext.startActivity(intent);
    }
}
