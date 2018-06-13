package com.spade.mazda.ui.profile.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.profile.view.interfaces.ProfileView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/20/17.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private Context context;
    private ProfileView profileView;
    private RealmDbHelper realmDbHelper;
    private DataSource dataSource;
    private User user;

    public ProfilePresenterImpl(Context context) {
        this.context = context;
        realmDbHelper = new RealmDbImpl();
        dataSource = DataSource.getInstance();
    }

    @Override
    public void setView(ProfileView view) {
        profileView = view;
    }

    @Override
    public void getUserData() {
        user = realmDbHelper.getUser(PrefUtils.getUserId(context));
        profileView.updateUI(user);
    }

    //todo call back scheme not specified yet
    @SuppressLint("CheckResult")
    @Override
    public void getUserCarHistory() {
        profileView.showLoading();
//        ApiHelper.getHistory(realmDbHelper.getUser(PrefUtils.getUserId(context)).getMotor(),realmDbHelper.getUser(PrefUtils.getUserId(context)).getChassis())
        ApiHelper.getHistory("211029", "252419")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(historyResponse -> {
                    profileView.hideLoading();
                    profileView.showHistory(historyResponse);
                }, throwable -> {
                    profileView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        profileView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCarDetails(int carID) {
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> {
                    profileView.setCarModel(carModel);
                    getCarYear(carModel, user.getCarYear());
                });
    }

    @SuppressLint("CheckResult")
    private void getCarYear(CarModel carModel, int yearID) {
        dataSource.getCarYearByID(carModel, yearID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carYear -> {
                    profileView.setCarYear(carYear.getYearName());
                    getCarTrim(carYear, user.getCarTrim());
                });
    }


    @SuppressLint("CheckResult")
    private void getCarTrim(CarYear carYear, int trimID) {
        dataSource.getCarTrimByID(carYear, trimID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carTrim -> {
                    getCarColor(carTrim, user.getCarColor());
                }, (Throwable throwable) -> {
                    Log.e(ProfilePresenterImpl.class.getSimpleName(), "Error--->" + throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    private void getCarColor(ModelTrim modelTrim, int colorID) {
        dataSource.getCarColorByID(modelTrim, colorID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carColor -> profileView.setCarColor(carColor.getColorName()));
    }


}
