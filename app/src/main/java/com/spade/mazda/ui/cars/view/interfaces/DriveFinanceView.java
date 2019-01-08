package com.spade.mazda.ui.cars.view.interfaces;

import com.spade.mazda.base.BaseView;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public interface DriveFinanceView extends BaseView {

    void showMonthlyInstallment(String value);

    void setCarModel(String carModel);

    void setCarYear(String carYear);

    void setCarTrim(String carTrim);

    void setSelectedProgram(String selectedProgram);

    void setNumberOfYears(String numberOfYears);

    void setCarModelError();

    void setCarYearError();

    void setCarTrimError();

    void setProgramError();

    void setNumberOfYearsError();

    void setDownPaymentError(int resID);

    void setOriginalPrice(String carPrice);

    void showProgressDialog();

    void hideProgressDialog();

    void showSuccessDialog();
}
