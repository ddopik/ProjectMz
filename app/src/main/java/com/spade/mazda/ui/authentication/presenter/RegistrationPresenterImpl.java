package com.spade.mazda.ui.authentication.presenter;

import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class RegistrationPresenterImpl implements RegistrationPresenter {

    private static RegistrationPresenterImpl registrationPresenterInstance;
    private RegistrationView registrationView;
    private String nameString, emailString, passwordString,
            mobileNumberString, birthDateString;
    private int modelPosition = 0, yearPosition = 0, trimPosition = 0, colorPosition = 0;
    private String chassisString, motorString, nationalIDString;
    private List<File> files = new ArrayList<>();


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

    @Override
    public void register(String appLang, String chassisString, String motorString, String nationalIdString,
                         int modelId, int yearId, int trimId, int colorId, File... imageFiles) {
        registrationView.showLoading();
        ApiHelper.registerUser(appLang, nameString, emailString, passwordString,
                mobileNumberString, birthDateString, chassisString, motorString,
                nationalIdString, modelId, yearId, trimId, colorId, imageFiles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registrationResponse -> {
                    registrationView.hideLoading();
                    registrationView.showMessage(registrationResponse.getRegistrationData().getMessage());
                    registrationView.navigateToActivate(emailString);
                }, throwable -> registrationView.hideLoading());
    }

    @Override
    public void getCars(String appLang) {
        DataSource dataSource = DataSource.getInstance();
        registrationView.showCarModels(dataSource.getCarModelList());
    }

    @Override
    public void getCarModels() {
        DataSource dataSource = DataSource.getInstance();
        registrationView.showCarModels(dataSource.getCarModelList());
    }


    public int getModelPosition() {
        return modelPosition;
    }

    @Override
    public void setModelPosition(int position) {
        this.modelPosition = position;
    }

    @Override
    public int getYearPosition() {
        return yearPosition;
    }

    @Override
    public void setYearPosition(int yearPosition) {
        this.yearPosition = yearPosition;
    }

    @Override
    public int getTrimPosition() {
        return trimPosition;
    }

    @Override
    public void setTrimPosition(int trimPosition) {
        this.trimPosition = trimPosition;
    }

    @Override
    public int getColorPosition() {
        return colorPosition;
    }

    @Override
    public void setColorPosition(int colorPosition) {
        this.colorPosition = colorPosition;
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
}
