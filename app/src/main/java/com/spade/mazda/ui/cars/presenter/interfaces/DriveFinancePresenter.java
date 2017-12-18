package com.spade.mazda.ui.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface DriveFinancePresenter extends BasePresenter<DriveFinanceView> {

    void getPrograms();

    void getCarYears(int carID);

    boolean isDownPaymentValid(String downPayment, Double carPrice);

    void calculateMonthlyInstallment(double carPrice, String downPayment, int numberOfYears);
}
