package com.spade.mazda.ui.profile.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.androidnetworking.error.ANError;
import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.LoginResponse;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.ui.general.view.dialog.ColorsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.ModelsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.TrimsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.YearsDialogFragment;
import com.spade.mazda.ui.general.view.interfaces.CarColorInterface;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;
import com.spade.mazda.ui.general.view.interfaces.CarTrimInterface;
import com.spade.mazda.ui.general.view.interfaces.CarYearInterface;
import com.spade.mazda.ui.profile.view.interfaces.EditCarView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class EditCarDetailsPresenterImpl implements
        EditCarPresenter,
        CarModelInterface,
        CarYearInterface,
        CarTrimInterface,
        CarColorInterface {

    public static final int FIRST_ITEM = 0;
    private EditCarView editCarView;
    private DataSource dataSource;
    private RealmDbHelper realmDbHelper;
    private Context context;

    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();
    private List<TrimColor> trimColors = new ArrayList<>();

    private CarModel carModel;
    private CarYear carYear;
    private ModelTrim modelTrim;
    private TrimColor trimColor;

    private User user;

    private String chassisString, motorString;

    private int modelId = -1, yearId = -1, trimId = -1, colorId = -1;

    public EditCarDetailsPresenterImpl(Context context) {
        realmDbHelper = new RealmDbImpl();
        dataSource = DataSource.getInstance();
        this.context = context;
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
            editCarView.showMessage(R.string.please_choose_car_model);
        } else {
            YearsDialogFragment yearsDialogFragment = new YearsDialogFragment();
            yearsDialogFragment.setCarYears(carYears);
            yearsDialogFragment.setCarYearInterface(this);
            yearsDialogFragment.show(fragmentManager, YearsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showCarColorsDialog(FragmentManager fragmentManager) {
        if (trimColors.isEmpty()) {
            editCarView.showMessage(R.string.please_choose_car_trim);
        } else {
            ColorsDialogFragment colorsDialogFragment = new ColorsDialogFragment();
            colorsDialogFragment.setTrimColors(trimColors);
            colorsDialogFragment.setCarColorInterface(this);
            colorsDialogFragment.show(fragmentManager, ColorsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showCarTrimsDialog(FragmentManager fragmentManager) {
        if (modelTrims.isEmpty()) {
            editCarView.showMessage(R.string.please_choose_car_year);
        } else {
            TrimsDialogFragment trimsDialogFragment = new TrimsDialogFragment();
            trimsDialogFragment.setModelTrimInterface(this);
            trimsDialogFragment.setModelTrims(modelTrims);
            trimsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void onYearSelected(CarYear carYear) {
        this.carYear = carYear;
        yearId = carYear.getYearID();
        modelTrims.clear();
        modelTrims.addAll(carYear.getModelTrims());
        editCarView.setCarYear(carYear.getYearName());
        if (modelTrims != null && !modelTrims.isEmpty()) {
            onTrimSelected(modelTrims.get(FIRST_ITEM));
        }
    }

    @Override
    public void onModelSelected(CarModel carModel) {
        this.carModel = carModel;
        modelId = carModel.getCarModelId();
        carYears.clear();
        carYears.addAll(carModel.getCarYears());
        editCarView.setCarModel(carModel.getCarModelName());
        if (carYears != null && !carYears.isEmpty()) {
            onYearSelected(carYears.get(FIRST_ITEM));
        }
    }

    @Override
    public void onTrimSelected(ModelTrim modelTrim) {
        this.modelTrim = modelTrim;
        trimId = modelTrim.getTrimId();
        trimColors.clear();
        trimColors.addAll(modelTrim.getTrimColors());
        editCarView.setCarTrim(modelTrim.getTrimName());
        if (trimColors != null && !trimColors.isEmpty()) {
            onColorSelected(trimColors.get(FIRST_ITEM));
        }
    }

    @Override
    public void onColorSelected(TrimColor trimColor) {
        this.trimColor = trimColor;
        colorId = trimColor.getColorID();
        editCarView.setCarColor(trimColor.getColorName());
    }

    @Override
    public boolean dataIsValid() {
        if (modelId == -1) {
            editCarView.setCarModelError();
            return false;
        }

        if (yearId == -1) {
            editCarView.setCarYearError();
            return false;
        }

        if (trimId == -1) {
            editCarView.setCarTrimError();
            return false;
        }

        if (colorId == -1) {
            editCarView.setCarColorError();
            return false;
        }

        if (chassisString == null || chassisString.isEmpty()) {
            editCarView.setChassisError();
            return false;
        }

        if (motorString == null || motorString.isEmpty()) {
            editCarView.setMotorError();
            return false;
        }

        return true;
    }

    @Override
    public void edit() {
        editCarView.showProgressDialog();
        ApiHelper.editCar(PrefUtils.getAppLang(context), PrefUtils.getUserToken(context),
                chassisString, motorString, modelId, yearId, trimId, colorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    saveUpdatedData(loginResponse);
                    editCarView.showMessage(R.string.data_updated);
                    editCarView.hideProgressDialog();
                    editCarView.finish();
                }, throwable -> {
                    editCarView.hideProgressDialog();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        editCarView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void getUser() {
        user = realmDbHelper.getUser(PrefUtils.getUserId(context));
        editCarView.setChassis(user.getChassis());
        editCarView.setMotor(user.getMotor());
        getCarDetails(user.getCarModel());
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public CarYear getCarYear() {
        return carYear;
    }

    public ModelTrim getModelTrim() {
        return modelTrim;
    }

    public TrimColor getTrimColor() {
        return trimColor;
    }

    public void setCarModel(CarModel carModel) {
        onModelSelected(carModel);
    }

    public void setCarYear(CarYear carYear) {
        onYearSelected(carYear);
    }

    public void setModelTrim(ModelTrim modelTrim) {
        onTrimSelected(modelTrim);
    }

    public void setTrimColor(TrimColor trimColor) {
        onColorSelected(trimColor);
    }

    @Override
    public void setChassisString(String chassis) {
        chassisString = chassis;
    }

    @Override
    public void setMotorString(String motor) {
        motorString = motor;
    }


    private void getCarDetails(int carID) {
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> {
                    onModelSelected(carModel);
//                    modelId = carModel.getCarModelId();
//                    editCarView.setCarModel(carModel.getCarModelName());
                    getCarYear(carModel, user.getCarYear());
                });
    }

    private void getCarYear(CarModel carModel, int yearID) {
        dataSource.getCarYearByID(carModel, yearID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carYear -> {
//                    yearId = carYear.getYearID();
//                    editCarView.setCarYear(carYear.getYearName());
                    onYearSelected(carYear);
                    getCarTrim(carYear, user.getCarTrim());
                });
    }

    private void getCarTrim(CarYear carYear, int trimID) {
        dataSource.getCarTrimByID(carYear, trimID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carTrim -> {
//                    trimId = carTrim.getTrimId();
//                    editCarView.setCarTrim(carTrim.getTrimName());
                    onTrimSelected(carTrim);
                    getCarColor(carTrim, user.getCarColor());
                });
    }

    private void getCarColor(ModelTrim modelTrim, int colorID) {
        //                    colorId = carColor.getColorID();
//                    editCarView.setCarColor(carColor.getColorName());
        dataSource.getCarColorByID(modelTrim, colorID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onColorSelected);
    }


    @Override
    public void setView(EditCarView view) {
        editCarView = view;
    }

    private void saveUpdatedData(LoginResponse response) {
        PrefUtils.setUserToken(context, response.getLoginData().getToken());
        realmDbHelper.saveUserOrUpdate(response.getLoginData().getUser(),
                response.getLoginData().getToken());
    }

    private void clearSeletedData() {

    }
}
