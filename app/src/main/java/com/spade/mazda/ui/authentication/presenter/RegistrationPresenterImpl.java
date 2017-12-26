package com.spade.mazda.ui.authentication.presenter;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;
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
import com.spade.mazda.utils.ErrorUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.spade.mazda.ui.authentication.view.fragment.RegistrationSecondStepFragment.LIST_SIZE;
import static com.spade.mazda.ui.profile.presenter.EditCarDetailsPresenterImpl.FIRST_ITEM;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class RegistrationPresenterImpl implements
        RegistrationPresenter,
        CarModelInterface,
        CarYearInterface,
        CarTrimInterface,
        CarColorInterface {

    private static RegistrationPresenterImpl registrationPresenterInstance;
    private RegistrationView registrationView;

    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();
    private List<TrimColor> trimColors = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    private CarModel carModel;
    private CarYear carYear;
    private ModelTrim modelTrim;
    private TrimColor trimColor;

    private String chassisString, motorString, nationalIDString, mobileNumberString,
            birthDateString, nameString, emailString, passwordString;

    private int modelId = -1, yearId = -1, trimId = -1, colorId = -1;


    public static RegistrationPresenterImpl getInstance() {
        if (registrationPresenterInstance == null) {
            registrationPresenterInstance = new RegistrationPresenterImpl();
        }
        return registrationPresenterInstance;
    }

    @Override
    public void setView(RegistrationView view) {
        registrationView = view;
    }


    @Override
    public void saveFirstStepData(String name, String email, String password, String phoneNumber, String birthDate) {
        this.nameString = name;
        this.emailString = email;
        this.passwordString = password;
        this.mobileNumberString = phoneNumber;
        this.birthDateString = birthDate;
//        registrationView.navigateToNextStep();
    }

    @Override
    public void pickImageFromCamera(BaseFragment baseFragment) {
        EasyImage.openCamera(baseFragment, 0);
    }

    @Override
    public void pickImageFromGallery(BaseFragment baseFragment) {
        EasyImage.openGallery(baseFragment, 0);
    }

//    @Override
//    public void register(String appLang, String chassisString, String motorString, String nationalIdString,
//                         int modelId, int yearId, int trimId, int colorId, File... imageFiles) {
//        registrationView.showLoading();
//        ApiHelper.registerUser(appLang, nameString, emailString, passwordString,
//                mobileNumberString, birthDateString, chassisString, motorString,
//                nationalIdString, modelId, yearId, trimId, colorId, imageFiles)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(registrationResponse -> {
//                    registrationView.hideLoading();
//                    registrationView.showMessage(registrationResponse.getRegistrationData().getMessage());
//                    registrationView.navigateToActivate(emailString);
//                }, throwable -> registrationView.hideLoading());
//    }

    @Override
    public void register(String appLang) {
        registrationView.showLoading();
        File[] imageFiles = files.toArray(new File[files.size()]);
        ApiHelper.registerUser(appLang, nameString, emailString, passwordString,
                mobileNumberString, birthDateString, chassisString, motorString,
                nationalIDString, modelId, yearId, trimId, colorId, imageFiles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registrationResponse -> {
                    registrationView.hideLoading();
                    registrationView.showMessage(registrationResponse.getRegistrationData().getMessage());
                    registrationView.navigateToActivate(emailString);
                }, throwable -> {
                    registrationView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        registrationView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    public String getChassisString() {
        return chassisString;
    }

    public void setChassisString(String chassisString) {
        this.chassisString = chassisString;
    }

    public String getMotorString() {
        return motorString;
    }

    public void setMotorString(String motorString) {
        this.motorString = motorString;
    }

    public String getNationalIDString() {
        return nationalIDString;
    }

    public void setNationalIDString(String nationalIDString) {
        this.nationalIDString = nationalIDString;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
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
            registrationView.showMessage(R.string.please_choose_car_model);
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
            registrationView.showMessage(R.string.please_choose_car_trim);
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
            registrationView.showMessage(R.string.please_choose_car_year);
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
        registrationView.setCarYear(carYear.getYearName());
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
        registrationView.setCarModel(carModel.getCarModelName());
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
        registrationView.setCarTrim(modelTrim.getTrimName());
        if (trimColors != null && !trimColors.isEmpty()) {
            onColorSelected(trimColors.get(FIRST_ITEM));
        }
    }

    @Override
    public void onColorSelected(TrimColor trimColor) {
        this.trimColor = trimColor;
        colorId = trimColor.getColorID();
        registrationView.setCarColor(trimColor.getColorName());
    }

    @Override
    public boolean dataIsValid() {
        if (modelId == -1) {
            registrationView.setCarModelError();
            return false;
        }

        if (yearId == -1) {
            registrationView.setCarYearError();
            return false;
        }

        if (trimId == -1) {
            registrationView.setCarTrimError();
            return false;
        }

        if (colorId == -1) {
            registrationView.setCarColorError();
            return false;
        }

        if (chassisString == null || chassisString.isEmpty()) {
            registrationView.setChassisError();
            return false;
        }

        if (motorString == null || motorString.isEmpty()) {
            registrationView.setMotorError();
            return false;
        }

        if (nationalIDString == null || nationalIDString.isEmpty()) {
            registrationView.setNationalIdError(R.string.national_id_number);
            return false;
        } else if (nationalIDString.length() != 14) {
            registrationView.setNationalIdError(R.string.national_id_constraint);
            return false;
        }

        if (files.size() < LIST_SIZE) {
            registrationView.showMessage(R.string.please_choose_national_id);
            return false;
        }
        return true;
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
}
