package com.spade.mazda.ui.cars.presenter.interfaces;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface DriveFinancePresenter extends BasePresenter<DriveFinanceView> {

    void getPrograms();

//    void getCarYears(int carID);

//    CarModel getCarModel();
//
//    CarYear getCarYear();
//
//    ModelTrim getModelTrim();


    void setCarModel(CarModel carModel);

    void setCarYear(CarYear carYear);

    void setModelTrim(ModelTrim modelTrim);

    void showCarModelsDialog();

    void showCarYearsDialog();

    void showCarTrimsDialog();

    void showProgramsDialog();

    void showYearsDialog();

    boolean dataIsValid(String downPayment);

    boolean isDownPaymentValid(String downPayment);

    void calculateMonthlyInstallment(double carPrice, String downPayment, int numberOfYears);

    void calculateMonthlyInstallment();

    void getCarDetails(int carID);

    void showUserDataFragment();

    void submitAsInterested();
}
