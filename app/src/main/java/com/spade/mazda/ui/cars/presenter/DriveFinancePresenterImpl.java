package com.spade.mazda.ui.cars.presenter;

import android.content.Context;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.cars.presenter.interfaces.DriveFinancePresenter;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public class DriveFinancePresenterImpl implements DriveFinancePresenter {

    private DriveFinanceView driveFinanceView;
    private Context context;
    private static final double DOWN_PAYMENT_PERCENTAGE = 0.2;
    private static final int MONTHS_PER_YEAR = 12;

    public DriveFinancePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(DriveFinanceView view) {
        driveFinanceView = view;
    }

    @Override
    public void getPrograms() {
        driveFinanceView.showLoading();
        ApiHelper.getPrograms(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programsResponse -> {
                    driveFinanceView.hideLoading();
                    driveFinanceView.showPrograms(programsResponse.getData());
                }, throwable -> {
                    driveFinanceView.hideLoading();
                });
    }

    @Override
    public void getCarYears(int carID) {
        DataSource dataSource = DataSource.getInstance();
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> driveFinanceView.showCarYears(carModel.getCarYears()));
    }

    @Override
    public boolean isDownPaymentValid(String downPayment, Double originalPrice) {
        try {
            double downPaymentValue = Double.parseDouble(downPayment);
            if (downPaymentValue < (originalPrice * DOWN_PAYMENT_PERCENTAGE)) {
                driveFinanceView.showMessage(R.string.down_payment_message_1);
                return false;
            } else if (downPaymentValue >= originalPrice) {
                driveFinanceView.showMessage(R.string.down_payment_message_2);
                return false;
            }
        } catch (NumberFormatException e) {
            driveFinanceView.showMessage(R.string.enter_valid_amount);
            return false;
        }
        return true;
    }

    @Override
    public void calculateMonthlyInstallment(double carPrice, String downPayment, int numberOfYears) {
        double downPaymentValue = Double.parseDouble(downPayment);
        double difference = carPrice - downPaymentValue;
        double loanAmount = difference + (difference * 0.1);
        int numberOfMonths = numberOfYears * MONTHS_PER_YEAR;
        int monthlyInstallmentValue = (int) (loanAmount / numberOfMonths);
        String monthlyInstallment = String.format(context.getString(R.string.currency), String.valueOf(monthlyInstallmentValue));
        driveFinanceView.showMonthlyInstallment(monthlyInstallment);
    }

}
