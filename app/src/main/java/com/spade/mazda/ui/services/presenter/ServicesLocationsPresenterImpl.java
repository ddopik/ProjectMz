package com.spade.mazda.ui.services.presenter;

import android.content.Context;

import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.ServicesLocationsView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public class ServicesLocationsPresenterImpl implements ServicesLocationsPresenter {

    private Context context;
    private ServicesLocationsView servicesLocationsView;
    private DataSource dataSource;

    public ServicesLocationsPresenterImpl(Context context) {
        this.context = context;
        dataSource = DataSource.getInstance();
    }

    @Override
    public void setView(ServicesLocationsView view) {
        this.servicesLocationsView = view;
    }

    @Override
    public void getServicesLocation(String type) {
        servicesLocationsView.showLoading();
        ApiHelper.getServicesLocation(PrefUtils.getAppLang(context), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(servicesLocationsResponseConsumer -> {
                    servicesLocationsView.hideLoading();
                    dataSource.setServicesLocations(servicesLocationsResponseConsumer.getData());
                    servicesLocationsView.showServicesLocations(servicesLocationsResponseConsumer.getData());
                }, throwable -> {
                    servicesLocationsView.hideLoading();
                });
    }

    @Override
    public void getLocations() {
        ApiHelper.getLocations(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locationsResponse -> {
                    servicesLocationsView.showLocations(locationsResponse.getLocationList());
                }, throwable -> {
                });
    }

    @Override
    public void getServiceLocationsByLocationId(int locationID) {
        dataSource.getServiceLocationByLocation(locationID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(servicesLocations -> servicesLocationsView.showServicesLocations(servicesLocations));
    }

    @Override
    public void getAllServiceLocation() {
        servicesLocationsView.showServicesLocations(dataSource.getServicesLocations());
    }
}
