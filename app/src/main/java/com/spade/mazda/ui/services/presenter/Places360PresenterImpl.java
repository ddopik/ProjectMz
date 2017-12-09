package com.spade.mazda.ui.services.presenter;

import android.content.Context;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.Places360View;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class Places360PresenterImpl implements Places360Presenter {
    private Places360View places360View;
    private Context context;

    public Places360PresenterImpl(Context context) {
        this.context = context;
    }


    @Override
    public void setView(Places360View view) {
        this.places360View = view;
    }

    @Override
    public void getPlaces() {
        places360View.showLoading();
        ApiHelper.get360(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(threeSixtyResponseConsumer -> {
                    places360View.hideLoading();
                    places360View.showPlaces(threeSixtyResponseConsumer.getData());
                }, throwable -> {
                });
    }
}
