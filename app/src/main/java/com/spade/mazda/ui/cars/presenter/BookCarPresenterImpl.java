package com.spade.mazda.ui.cars.presenter;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.presenter.interfaces.BookCarPresenter;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class BookCarPresenterImpl implements BookCarPresenter {
    private BookCarView bookCarView;
    private Context context;
    private RealmDbHelper realmDbHelper;
    private DataSource dataSource;

    public BookCarPresenterImpl(Context context) {
        this.context = context;
        realmDbHelper = new RealmDbImpl();
        dataSource = DataSource.getInstance();

    }

    @Override
    public void setView(BookCarView view) {
        this.bookCarView = view;
    }

    @Override
    public void bookCar(String name, String mobileNumber, String modelID, String yearId, String trimId, String showRoomID) {
        bookCarView.showProgressDialog();
        ApiHelper.bookCar(PrefUtils.getAppLang(context)
                , PrefUtils.getUserToken(context)
                , name, mobileNumber, modelID, yearId, trimId, showRoomID, new ApiHelper.ApiCallBack() {
                    @Override
                    public void onSuccess() {
                        bookCarView.hideProgressDialog();
                        bookCarView.showConfirmationDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        bookCarView.hideProgressDialog();
                        bookCarView.showMessage(message);
                    }
                });
    }

    @Override
    public void getUser() {
        User user = realmDbHelper.getUser(PrefUtils.getUserId(context));
        if (user != null) {
            bookCarView.showName(user.getUserName());
            bookCarView.showMobileNumber(user.getUserPhone());
        }
    }

    @Override
    public void getCarModels() {
        bookCarView.showCarModels(dataSource.getCarModelList());
    }

    @Override
    public void getCarModel(int carID) {
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> bookCarView.setSelectedCar(carModel));
    }

    @Override
    public void getShowRooms() {
        bookCarView.showLoading();
        ApiHelper.getBranches(PrefUtils.getAppLang(context), String.valueOf(FindUsFragment.SHOWROOMS_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    bookCarView.hideLoading();
                    if (branchesResponse != null && branchesResponse.getBranchesList() != null) {
                        bookCarView.showNearestShowRooms(branchesResponse.getBranchesList());
                    }
                }, throwable -> {
                    bookCarView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        bookCarView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }
}
