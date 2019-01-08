package com.spade.mazda.ui.authentication.presenter;

import android.annotation.SuppressLint;

import com.androidnetworking.error.ANError;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationSecondStepView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.utils.ErrorUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegistrationPresenterStepTwoPresenterImpl implements RegistrationPresenterStepTwoPresenter {

    private RegistrationSecondStepView registrationSecondStepView;
    private List<File> files = new ArrayList<>();
    private int modelId = -1, yearId = -1, trimId = -1, colorId = -1;

    @SuppressLint("CheckResult")
    @Override
    public void register(String appLang, String nameString, String emailString, String passwordString, String mobileNumberString, String birthDateString) {
        registrationSecondStepView.showLoading();
        File[] imageFiles = RegistrationPresenterImpl.files.toArray(new File[RegistrationPresenterImpl.files.size()]);
        ApiHelper.registerUser(appLang, nameString, emailString, passwordString, mobileNumberString, birthDateString, RegistrationPresenterImpl.chassisID, RegistrationPresenterImpl.motorID, RegistrationPresenterImpl.nationalID, RegistrationPresenterImpl.modelId, RegistrationPresenterImpl.yearId, RegistrationPresenterImpl.trimId, RegistrationPresenterImpl.colorId, imageFiles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registrationResponse -> {
                    registrationSecondStepView.hideLoading();
                    registrationSecondStepView.showMessage(registrationResponse.getRegistrationData().getMessage());
                    registrationSecondStepView.navigateToActivate(emailString);
                }, throwable -> {
                    registrationSecondStepView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        registrationSecondStepView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void setView(RegistrationSecondStepView registrationSecondStepView) {
        this.registrationSecondStepView = registrationSecondStepView;
    }
}
