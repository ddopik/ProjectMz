package com.spade.mazda.ui.services.presenter;

import android.content.Context;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.DriveFinanceView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public class DriveFinancePresenterImpl implements DriveFinancePresenter {

    private Context context;
    private DriveFinanceView driveFinanceView;

    public DriveFinancePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(DriveFinanceView view) {
        this.driveFinanceView = view;
    }

    @Override
    public void getOffers() {
        driveFinanceView.showLoading();
        ApiHelper.getOffers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(offersResponse -> {
                    driveFinanceView.hideLoading();
                    driveFinanceView.showOffers(offersResponse.getData());
                }, throwable -> {
                    driveFinanceView.hideLoading();
                });
    }

    @Override
    public void getPrograms() {
        driveFinanceView.showLoading();
        ApiHelper.getPrograms(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programsResponse -> {
                    driveFinanceView.hideLoading();
                    driveFinanceView.showPrograms(programsResponse.getData());
                }, throwable -> {
                    driveFinanceView.hideLoading();
                });
    }
}
