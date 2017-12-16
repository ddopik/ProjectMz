package com.spade.mazda.ui.find_us.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.interfaces.BranchesView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class BranchesPresenterImpl implements BranchesPresenter {

    private Context context;
    private BranchesView branchesView;

    public BranchesPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(BranchesView view) {
        this.branchesView = view;
    }

    @Override
    public void getBranches(int type, String appLang) {
        branchesView.showLoading();
        ApiHelper.getBranches(appLang, String.valueOf(type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    branchesView.hideLoading();
                    branchesView.showBranches(branchesResponse.getBranchesList());
                }, throwable -> {
                });
    }

    public void getMapPins(List<Branch> branchList) {
        getMapPinsLocations(branchList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(latLngList -> {
                    branchesView.hideLoading();
                    branchesView.showPins(latLngList);
                }, throwable -> {
                });
    }

    private Observable<List<LatLng>> getMapPinsLocations(List<Branch> branchList) {
        return Observable.create(latLngObservableEmitter -> {
            List<LatLng> latLngList = new ArrayList<>();
            for (Branch branch : branchList) {
                LatLng latLng = new LatLng(Double.parseDouble(branch.getBranchLat()), Double.parseDouble(branch.getBranchLng()));
                latLngList.add(latLng);
            }
            latLngObservableEmitter.onNext(latLngList);
            latLngObservableEmitter.onComplete();
        });
    }

    @Override
    public void getCities(List<Branch> branches) {
        DataSource.getCitiesList(branches, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> {
                    branchesView.showCities(cities);
                });
    }

}
