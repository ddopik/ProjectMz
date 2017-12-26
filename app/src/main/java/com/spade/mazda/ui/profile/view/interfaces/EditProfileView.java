package com.spade.mazda.ui.profile.view.interfaces;

import com.spade.mazda.base.BaseView;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public interface EditProfileView extends BaseView {

    void setUserName(String name);

    void setUserEmail(String email);

    void setUserPhone(String phone);

    void setUserBirthDate(String birthDate);

    void setCarImage(String carImage);

    void setNationalID(String nationalID);

    void setCarModel(String carModel);

    void setCarYear(String carYear);

    void setCarTrim(String carTrim);

    void setCarColor(String carColor);

    void setChassis(String chassis);

    void setMotor(String motor);

    void setNameError();

    void setPhoneError();

    void setNationalIDError(int resID);

    void setBirthDateError();

    void showLoading();

    void hideLoading();

    void finish();
}
