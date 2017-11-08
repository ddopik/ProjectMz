package com.spade.mazda.services.presenter;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.services.view.SparePartsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsPresenterImpl implements SparePartsPresenter {
    private SparePartsView sparePartsView;


    @Override
    public void setView(SparePartsView view) {
        this.sparePartsView = view;
    }

    @Override
    public void getSpareParts(String appLang) {
        sparePartsView.showLoading();
        ApiHelper.getSpareParts(appLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sparePartsResponse -> {
                    sparePartsView.hideLoading();
                    sparePartsView.showSparParts(sparePartsResponse.getSparePartCategories());
                }, throwable -> {
                });
    }
}
