package com.spade.mazda.ui.services.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.SparePartsView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsPresenterImpl implements SparePartsPresenter {
    private SparePartsView sparePartsView;
    private Context context;

    public SparePartsPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(SparePartsView view) {
        this.sparePartsView = view;
    }

    @Override
    public void getCarModels() {
        DataSource dataSource = DataSource.getInstance();
        sparePartsView.showCarModels(dataSource.getCarModelList());
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSpareParts(String trimId) {
        sparePartsView.showLoading();
        ApiHelper.getSpareParts(PrefUtils.getAppLang(context), trimId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sparePartsResponse -> {
                    sparePartsView.hideLoading();
                    sparePartsView.showSparParts(sparePartsResponse.getSparePartCategories());
                }, throwable -> {
                    Log.e(SparePartsPresenterImpl.class.getSimpleName(), "Error--->" + throwable.getMessage());
                });
    }
}
