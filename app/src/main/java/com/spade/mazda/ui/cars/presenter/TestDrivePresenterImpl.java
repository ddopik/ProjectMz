package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.androidnetworking.error.ANError;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.presenter.interfaces.TestDrivePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.general.view.dialog.BranchesDialogFragment;
import com.spade.mazda.ui.general.view.dialog.CitiesDialogFragment;
import com.spade.mazda.ui.general.view.dialog.ModelsDialogFragment;
import com.spade.mazda.ui.general.view.interfaces.BranchesInterface;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;
import com.spade.mazda.ui.general.view.interfaces.CitiesInterface;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class TestDrivePresenterImpl implements
        TestDrivePresenter,
        PickDateDialog.OnDateSet,
        CarModelInterface,
        BranchesInterface,
        CitiesInterface {

    private TestDriveView testDriveView;
    private Context context;
    private DataSource dataSource;
    private List<Branch> allBranches = new ArrayList<>();
    private List<Branch> filteredBranches = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    private String dateString;
    private int modelId = -1, branchId = -1, cityID = -1;


    public TestDrivePresenterImpl(Context context) {
        this.context = context;
        dataSource = DataSource.getInstance();

    }

    @Override
    public void setView(TestDriveView view) {
        this.testDriveView = view;
    }

    @Override
    public void requestTest() {
        testDriveView.showProgressDialog();
        ApiHelper.requestDriveTest(PrefUtils.getAppLang(context)
                , PrefUtils.getUserToken(context)
                , String.valueOf(modelId), String.valueOf(cityID), String.valueOf(branchId), dateString, new ApiHelper.ApiCallBack() {
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
    public void getShowRooms() {
        testDriveView.showLoading();
        ApiHelper.getBranches(PrefUtils.getAppLang(context), String.valueOf(FindUsFragment.SHOWROOMS_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branchesResponse -> {
                    testDriveView.hideLoading();
                    if (branchesResponse != null && branchesResponse.getBranchesList() != null) {
                        allBranches.addAll(branchesResponse.getBranchesList());
                        getCities(allBranches);
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
    public void showCarModelsDialog(FragmentManager fragmentManager) {
        ModelsDialogFragment modelsDialogFragment = new ModelsDialogFragment();
        modelsDialogFragment.setModelActions(this);
        modelsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
    }

    @Override
    public void showBranchesDialog(FragmentManager fragmentManager) {
        if (!filteredBranches.isEmpty()) {
            BranchesDialogFragment branchesDialogFragment = new BranchesDialogFragment();
            branchesDialogFragment.setBranchesInterface(this);
            branchesDialogFragment.setBranches(filteredBranches);
            branchesDialogFragment.show(fragmentManager, BranchesDialogFragment.class.getSimpleName());
        } else {
            testDriveView.setCityError();
        }
    }


    @Override
    public void showCitiesDialog(FragmentManager fragmentManager) {
        CitiesDialogFragment citiesDialogFragment = new CitiesDialogFragment();
        citiesDialogFragment.setCitiesInterface(this);
        citiesDialogFragment.setCities(cities);
        citiesDialogFragment.show(fragmentManager, BranchesDialogFragment.class.getSimpleName());
    }

    public void showDatePicker(FragmentManager fragmentManager) {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(fragmentManager, PickDateDialog.class.getSimpleName());
    }


    @Override
    public void onDateSet(int year, int month, int day) {
        dateString = year + "-" + (month + 1) + "-" + day;
        testDriveView.setDate(dateString);
    }

    @Override
    public void getCities(List<Branch> branches) {
        DataSource.getCitiesList(branches, context, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> this.cities.addAll(cities));
    }

    public boolean dataIsValid() {
        if (modelId == -1) {
            testDriveView.setCarModelError();
            return false;
        }

        if (cityID == -1) {
            testDriveView.setCityError();
            return false;
        }

        if (branchId == -1) {
            testDriveView.setBranchError();
            return false;
        }


        if (dateString.isEmpty()) {
            testDriveView.setDateError();
            return false;
        }
        return true;
    }

    @Override
    public void onModelSelected(CarModel carModel) {
        modelId = carModel.getCarModelId();
        testDriveView.setCarModel(carModel.getCarModelName());
    }

    @Override
    public void onBranchSelected(Branch branch) {
        testDriveView.setBranchName(branch.getName());
        branchId = branch.getId();
    }

    @Override
    public void onCitySelected(City city) {
        cityID = city.getCityId();
        testDriveView.setCity(city.getCityName());
        dataSource.getBranchByCityId(allBranches, cityID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branches -> {
                    filteredBranches.clear();
                    filteredBranches.addAll(branches);
                });
    }
}
