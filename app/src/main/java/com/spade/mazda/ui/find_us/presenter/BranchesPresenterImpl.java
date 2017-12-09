package com.spade.mazda.ui.find_us.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.interfaces.BranchesView;
import com.spade.mazda.network.ApiHelper;

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
                    branchesView.showBranches(branchesResponse.getBranchesList()
                    );
                }, throwable -> {
                });
    }

    @Override
    public Observable<LatLng> getMapPins(List<Branch> branchList) {
        return Observable.create(latLngObservableEmitter -> {
            for (Branch branch : branchList) {
                LatLng latLng = new LatLng(Double.parseDouble(branch.getBranchLat()), Double.parseDouble(branch.getBranchLng()));
                latLngObservableEmitter.onNext(latLng);
            }
            latLngObservableEmitter.onComplete();
        });
    }

//    public void latLngObservable(List<Branch> branchList) {
//
//    }
//
//    private void getLatLngList(List<Branch> branchList) {
//        List<LatLng> latLngList = new ArrayList<>();
//        for (Branch branch : branchList) {
//            LatLng latLng = new LatLng(Double.parseDouble(branch.getBranchLat()), Double.parseDouble(branch.getBranchLng()));
//            latLngList.add(latLng);
//        }
//
//    }
}
