package com.spade.mazda.ui.services.presenter;

import android.content.Context;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.FinanceView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public class FinancePresenterImpl implements FinancePresenter {

    private Context context;
    private FinanceView financeView;

    public FinancePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(FinanceView view) {
        this.financeView = view;
    }

    @Override
    public void getOffers() {
        financeView.showLoading();
        ApiHelper.getOffers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(offersResponse -> {
                    financeView.hideLoading();
                    financeView.showOffers(offersResponse.getData());
                }, throwable -> {
                    financeView.hideLoading();
                });
    }

    @Override
    public void getPrograms() {
        financeView.showLoading();
        ApiHelper.getPrograms(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programsResponse -> {
                    financeView.hideLoading();
                    financeView.showPrograms(programsResponse.getData());
                }, throwable -> {
                    financeView.hideLoading();
                });
    }
}
