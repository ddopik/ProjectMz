package com.spade.mazda.ui.services.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.androidnetworking.error.ANError;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.services.view.interfaces.PeriodicView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class PeriodicServicesPresenterImpl implements PeriodicServicePresenter, PickDateDialog.OnDateSet {

    private Context context;
    private PeriodicView periodicView;

    public PeriodicServicesPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(PeriodicView view) {
        periodicView = view;
    }

    @Override
    public void getServiceCenters() {
        periodicView.showLoading();
        ApiHelper.getBranches(PrefUtils.getAppLang(context), String.valueOf(FindUsFragment.SERVICE_CENTER_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    periodicView.hideLoading();
                    if (branchesResponse != null && branchesResponse.getBranchesList() != null) {
                        periodicView.showServiceCenters(branchesResponse.getBranchesList());
                    }
                }, throwable -> {
                    periodicView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        periodicView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void getKiloMetersServices() {
        periodicView.showLoading();
        ApiHelper.getKilometers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kilometersResponse -> {
                    periodicView.hideLoading();
                    if (kilometersResponse != null && kilometersResponse.getData() != null) {
                        periodicView.showKilometers(kilometersResponse.getData());
                    }
                }, throwable -> {
                    periodicView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        periodicView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void openDatePicker(FragmentManager fragmentManager) {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(fragmentManager, PickDateDialog.class.getSimpleName());
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        String dateString = year + "-" + (month + 1) + "-" + day;
        periodicView.setDate(dateString);
    }
}
