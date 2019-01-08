package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.androidnetworking.error.ANError;
import com.spade.mazda.R;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.presenter.interfaces.BookCarPresenter;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.general.view.dialog.BranchesDialogFragment;
import com.spade.mazda.ui.general.view.dialog.ModelsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.TrimsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.YearsDialogFragment;
import com.spade.mazda.ui.general.view.interfaces.BranchesInterface;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;
import com.spade.mazda.ui.general.view.interfaces.CarTrimInterface;
import com.spade.mazda.ui.general.view.interfaces.CarYearInterface;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.spade.mazda.ui.profile.presenter.EditCarDetailsPresenterImpl.FIRST_ITEM;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class BookCarPresenterImpl implements
        BookCarPresenter,
        CarModelInterface,
        CarYearInterface,
        CarTrimInterface,
        BranchesInterface {

    private BookCarView bookCarView;
    private Context context;
    private RealmDbHelper realmDbHelper;
    //    private DataSource dataSource;
    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();

//    private CarModel carModel;
//    private CarYear carYear;
//    private ModelTrim modelTrim;

    private String mobileNumberString, nameString;

    private int modelId = -1, yearId = -1, trimId = -1, branchId = -1;

    public BookCarPresenterImpl(Context context) {
        this.context = context;
        realmDbHelper = new RealmDbImpl();
//        dataSource = DataSource.getInstance();
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
    public void bookCar() {
        bookCarView.showProgressDialog();
        ApiHelper.bookCar(PrefUtils.getAppLang(context)
                , PrefUtils.getUserToken(context)
                , nameString, mobileNumberString, String.valueOf(modelId), String.valueOf(yearId), String.valueOf(trimId), String.valueOf(branchId), new ApiHelper.ApiCallBack() {
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
    public void setName(String name) {
        nameString = name;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        mobileNumberString = mobileNumber;
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
//        bookCarView.showCarModels(dataSource.getCarModelList());
    }

    @Override
    public void getCarModel(int carID) {
//        dataSource.getCarModel(carID)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(carModel -> bookCarView.setSelectedCar(carModel));
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

    @Override
    public void showCarModelsDialog(FragmentManager fragmentManager) {
        ModelsDialogFragment modelsDialogFragment = new ModelsDialogFragment();
        modelsDialogFragment.setModelActions(this);
        modelsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
    }

    @Override
    public void showCarYearsDialog(FragmentManager fragmentManager) {
        if (carYears.isEmpty()) {
            bookCarView.showMessage(R.string.please_choose_car_model);
        } else {
            YearsDialogFragment yearsDialogFragment = new YearsDialogFragment();
            yearsDialogFragment.setCarYears(carYears);
            yearsDialogFragment.setCarYearInterface(this);
            yearsDialogFragment.show(fragmentManager, YearsDialogFragment.class.getSimpleName());
        }
    }


    @Override
    public void showCarTrimsDialog(FragmentManager fragmentManager) {
        if (modelTrims.isEmpty()) {
            bookCarView.showMessage(R.string.please_choose_car_year);
        } else {
            TrimsDialogFragment trimsDialogFragment = new TrimsDialogFragment();
            trimsDialogFragment.setModelTrimInterface(this);
            trimsDialogFragment.setModelTrims(modelTrims);
            trimsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showBranches(FragmentManager fragmentManager, List<Branch> branches) {
        BranchesDialogFragment branchesDialogFragment = new BranchesDialogFragment();
        branchesDialogFragment.setBranchesInterface(this);
        branchesDialogFragment.setBranches(branches);
        branchesDialogFragment.show(fragmentManager, BranchesDialogFragment.class.getSimpleName());
    }

    @Override
    public void onYearSelected(CarYear carYear) {
//        this.carYear = carYear;
        yearId = carYear.getYearID();
        modelTrims.clear();
        modelTrims.addAll(carYear.getModelTrims());
        bookCarView.setCarYear(carYear.getYearName());
        if (modelTrims != null && !modelTrims.isEmpty()) {
            onTrimSelected(modelTrims.get(FIRST_ITEM));
        }
    }

    @Override
    public void onModelSelected(CarModel carModel) {
//        this.carModel = carModel;
        modelId = carModel.getCarModelId();
        carYears.clear();
        carYears.addAll(carModel.getCarYears());
        bookCarView.setCarModel(carModel.getCarModelName());
        if (carYears != null && !carYears.isEmpty()) {
            onYearSelected(carYears.get(FIRST_ITEM));
        }
    }

    @Override
    public void onTrimSelected(ModelTrim modelTrim) {
//        this.modelTrim = modelTrim;
        trimId = modelTrim.getTrimId();
        bookCarView.setCarTrim(modelTrim.getTrimName());
    }

    @Override
    public boolean isDataValid() {
        if (nameString == null || nameString.isEmpty()) {
            bookCarView.setNameError();
            return false;
        }
        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            bookCarView.setMobileNumberError();
            return false;
        }
        if (modelId == -1) {
            bookCarView.setCarModelError();
            return false;
        }


        if (yearId == -1) {
            bookCarView.setCarYearError();
            return false;
        }

        if (trimId == -1) {
            bookCarView.setCarTrimError();
            return false;
        }

        if (branchId == -1) {
            bookCarView.setBranchError();
            return false;
        }
        return true;
    }

    @Override
    public void onBranchSelected(Branch branch) {
        bookCarView.setBranchName(branch.getName());
    }
}
