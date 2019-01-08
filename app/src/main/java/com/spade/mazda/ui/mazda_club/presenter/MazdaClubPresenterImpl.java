package com.spade.mazda.ui.mazda_club.presenter;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.mazda_club.view.MazdaClubView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class MazdaClubPresenterImpl implements MazdaClubPresenter {
    private MazdaClubView mazdaClubView;
    private Context context;

    public MazdaClubPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(MazdaClubView view) {
        mazdaClubView = view;
    }

    @Override
    public void getMazdaClubTiers() {
        mazdaClubView.showLoading();
        ApiHelper.getMazdaClubTiers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mazdaClubResponse -> {
                    mazdaClubView.hideLoading();
                    mazdaClubView.showMazdaClubsTiers(mazdaClubResponse.getMazdaClubData());
                }, throwable -> {
                    mazdaClubView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        mazdaClubView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void subscribe() {

    }
}
