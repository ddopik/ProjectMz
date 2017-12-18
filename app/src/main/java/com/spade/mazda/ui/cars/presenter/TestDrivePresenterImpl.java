package com.spade.mazda.ui.cars.presenter;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.cars.presenter.interfaces.TestDrivePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class TestDrivePresenterImpl implements TestDrivePresenter {
    private TestDriveView testDriveView;
    private Context context;
    private DataSource dataSource;

    public TestDrivePresenterImpl(Context context) {
        this.context = context;
        dataSource = DataSource.getInstance();

    }

    @Override
    public void setView(TestDriveView view) {
        this.testDriveView = view;
    }


    @Override
    public void requestTest(String modelID, String cityId, String showRoomID, String date) {
        testDriveView.showProgressDialog();
        ApiHelper.requestDriveTest(PrefUtils.getAppLang(context)
                , PrefUtils.getUserToken(context)
                , modelID, cityId, showRoomID, date, new ApiHelper.ApiCallBack() {
                    @Override
                    public void onSuccess() {
                        testDriveView.hideProgressDialog();
                        testDriveView.showConfirmationDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        testDriveView.hideProgressDialog();
                        testDriveView.showMessage(message);
                    }
                });
    }

    @Override
    public void getCarModels() {
        testDriveView.showCarModels(dataSource.getCarModelList());
    }


    @Override
    public void getShowRooms() {
        testDriveView.showLoading();
        ApiHelper.getBranches(PrefUtils.getAppLang(context), String.valueOf(FindUsFragment.SHOWROOMS_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    testDriveView.hideLoading();
                    if (branchesResponse != null && branchesResponse.getBranchesList() != null) {
                        testDriveView.showNearestShowRooms(branchesResponse.getBranchesList());
                    }
                }, throwable -> {
                    testDriveView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        testDriveView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void getCities(List<Branch> branches) {
        DataSource.getCitiesList(branches, context, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> testDriveView.showCities(cities));
    }
}
