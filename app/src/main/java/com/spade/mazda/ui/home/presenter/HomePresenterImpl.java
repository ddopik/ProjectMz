package com.spade.mazda.ui.home.presenter;

import android.content.Context;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.home.view.HomeView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/6/17.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;
    private Context context;

    public HomePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(HomeView view) {
        this.homeView = view;
    }

    @Override
    public void getOffers() {
        homeView.showLoading();
        ApiHelper.getOffers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(offersResponse -> {
                    homeView.hideLoading();
                    homeView.showOffers(offersResponse.getData());
                }, throwable -> {
                });
    }
}
