package com.spade.mazda.ui.services.presenter;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.spade.mazda.R;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.interfaces.RequestSparePartsView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/15/17.
 */

public class RequestSparePartPresenterImpl implements RequestSparePartsPresenter {

    private RequestSparePartsView requestSparePartsView;
    private Context context;

    public RequestSparePartPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(RequestSparePartsView view) {
        requestSparePartsView = view;
    }

    @Override
    public void getSparePartsOutlets(String type) {
        requestSparePartsView.showLoading();
        ApiHelper.getBranches(PrefUtils.getAppLang(context), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    requestSparePartsView.hideLoading();
                    requestSparePartsView.showSparePartsOutLets(branchesResponse.getBranchesList());
                }, throwable -> {
                    requestSparePartsView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        requestSparePartsView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void getSpareParts() {
        requestSparePartsView.showLoading();
        ApiHelper.getSpareParts(PrefUtils.getAppLang(context), null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sparePartsResponse -> {
                    requestSparePartsView.hideLoading();
                    requestSparePartsView.showSparePartsCategories(sparePartsResponse.getSparePartCategories());
                }, throwable -> {
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        requestSparePartsView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void requestSparePart(String sparePartCategoryID,
                                 String sparePartId, String branchID, String description) {
        requestSparePartsView.showLoading();
        ApiHelper.requestSparePart(PrefUtils.getAppLang(context), PrefUtils.getUserToken(context)
                , sparePartCategoryID, sparePartId, branchID, description, new ApiHelper.ApiCallBack() {
                    @Override
                    public void onSuccess() {
                        requestSparePartsView.hideLoading();
                        requestSparePartsView.showConfirmationDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        requestSparePartsView.hideLoading();
                        requestSparePartsView.showMessage(message);
                    }
                });
    }


}
