package com.spade.mazda.ui.profile.view.interfaces;

import com.spade.mazda.base.BaseView;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface EditCarView extends BaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void showConfirmationDialog();

    void finish();

    void setCarModel(String carModel);

    void setCarYear(String carYear);

    void setCarTrim(String carTrim);

    void setCarColor(String carColor);

    void setChassis(String chassis);

    void setMotor(String motor);

    void setCarModelError();

    void setCarYearError();

    void setCarTrimError();

    void setCarColorError();

    void setChassisError();

    void setMotorError();
}

