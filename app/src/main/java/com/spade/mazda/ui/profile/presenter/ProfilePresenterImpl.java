package com.spade.mazda.ui.profile.presenter;

import android.content.Context;

import com.spade.mazda.base.DataSource;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.profile.view.interfaces.ProfileView;
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

    @Override
    public void getUserCarHistory(int userID) {

    }

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

    private void getCarYear(CarModel carModel, int yearID) {
        dataSource.getCarYearByID(carModel, yearID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carYear -> {
                    profileView.setCarYear(carYear.getYearName());
                    getCarTrim(carYear, user.getCarTrim());
                });
    }

    private void getCarTrim(CarYear carYear, int trimID) {
        dataSource.getCarTrimByID(carYear, trimID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carTrim -> getCarColor(carTrim, user.getCarColor()));
    }

    private void getCarColor(ModelTrim modelTrim, int colorID) {
        dataSource.getCarColorByID(modelTrim, colorID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carColor -> profileView.setCarColor(carColor.getColorName()));
    }
}
