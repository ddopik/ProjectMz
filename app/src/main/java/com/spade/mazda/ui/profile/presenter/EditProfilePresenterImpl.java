package com.spade.mazda.ui.profile.presenter;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.LoginResponse;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.profile.view.interfaces.EditProfileView;
import com.spade.mazda.utils.ErrorUtils;
import com.spade.mazda.utils.PrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class EditProfilePresenterImpl implements EditProfilePresenter {

    private EditProfilePresenterImpl editProfilePresenter;
    private EditProfileView editProfileView;
    private Context context;
    private RealmDbHelper realmDbHelper;
    private DataSource dataSource;
    //    private User user;
    private List<File> files = new ArrayList<>();
    private File userImage;


    private String nationalIDString, mobileNumberString,
            birthDateString, nameString, emailString;
    private int carModel, carYear, carTrim, carColor;

    public EditProfilePresenterImpl(Context context) {
        this.context = context;
        realmDbHelper = new RealmDbImpl();
        dataSource = DataSource.getInstance();
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
    public void setBirthDate(String birthDate) {
        birthDateString = birthDate;
    }

    @Override
    public void setEmail(String email) {
        emailString = email;
    }

    @Override
    public void setNationalId(String nationalID) {
        nationalIDString = nationalID;
    }

    @Override
    public void getUserData() {
        User user = realmDbHelper.getUser(PrefUtils.getUserId(context));
        editProfileView.setUserName(user.getUserName());
        editProfileView.setUserPhone(user.getUserPhone());
        editProfileView.setUserEmail(user.getUserEmail());
        editProfileView.setUserBirthDate(user.getBirthDate());
        editProfileView.setNationalID(user.getNationalId());
    }

    @Override
    public void getCarData() {
        User user = realmDbHelper.getUser(PrefUtils.getUserId(context));
        editProfileView.setChassis(user.getChassis());
        editProfileView.setMotor(user.getMotor());
        carModel = user.getCarModel();
        carYear = user.getCarYear();
        carTrim = user.getCarTrim();
        carColor = user.getCarColor();
        getCarDetails(carModel);
    }

    public void getCarDetails(int carID) {
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> {
                    editProfileView.setCarModel(carModel.getCarModelName());
                    editProfileView.setCarImage(carModel.getCarModelImage());
                    getCarYear(carModel, carYear);
                });
    }

    private void getCarYear(CarModel carModel, int yearID) {
        dataSource.getCarYearByID(carModel, yearID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carYear -> {
                    editProfileView.setCarYear(carYear.getYearName());
                    getCarTrim(carYear, carTrim);
                });
    }

    private void getCarTrim(CarYear carYear, int trimID) {
        dataSource.getCarTrimByID(carYear, trimID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carTrim -> {
                    editProfileView.setCarTrim(carTrim.getTrimName());
                    getCarColor(carTrim, carColor);
                });
    }

    private void getCarColor(ModelTrim modelTrim, int colorID) {
        dataSource.getCarColorByID(modelTrim, colorID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trimColor -> editProfileView.setCarColor(trimColor.getColorName()));
    }

    @Override
    public void pickImageFromCamera(BaseFragment baseFragment) {
        EasyImage.openCamera(baseFragment, 0);
    }

    @Override
    public void pickImageFromGallery(BaseFragment baseFragment) {
        EasyImage.openGallery(baseFragment, 0);
    }


    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public void setUserImage(File userImageFile) {
        this.userImage = userImageFile;
    }


    @Override
    public boolean isDataValid() {

        if (nameString == null || nameString.isEmpty()) {
            editProfileView.setNameError();
            return false;
        }

        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            editProfileView.setPhoneError();
            return false;
        }

//        if (nationalIDString == null || nationalIDString.isEmpty()) {
//            editProfileView.setNationalIDError(R.string.national_id_number);
//            return false;
//        } else if (nationalIDString.length() != 14) {
//            editProfileView.setNationalIDError(R.string.national_id_constraint);
//            return false;
//        }
        if (birthDateString == null || birthDateString.isEmpty()) {
            editProfileView.setBirthDateError();
            return false;
        }

        if (files.size() == 1) {
            editProfileView.showMessage(R.string.please_choose_national_id);
            return false;
        }
        return true;
    }

    @Override
    public void editProfile() {
        editProfileView.showLoading();
        File[] imageFiles = files.toArray(new File[files.size()]);
        ApiHelper.editProfile(PrefUtils.getAppLang(context), PrefUtils.getUserToken(context), nationalIDString, nameString,
                mobileNumberString, birthDateString, userImage, imageFiles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    saveUpdatedData(loginResponse);
                    editProfileView.showMessage(R.string.data_updated);
                    editProfileView.hideLoading();
                    editProfileView.finish();
                }, throwable -> {
                    editProfileView.hideLoading();
                    if (throwable != null) {
                        ANError anError = (ANError) throwable;
                        editProfileView.showMessage(ErrorUtils.getErrors(anError));
                    }
                });
    }

    @Override
    public void setView(EditProfileView view) {
        editProfileView = view;
    }

    private void saveUpdatedData(LoginResponse response) {
        PrefUtils.setUserToken(context, response.getLoginData().getToken());
        realmDbHelper.saveUserOrUpdate(response.getLoginData().getUser(),
                response.getLoginData().getToken());
    }
}
