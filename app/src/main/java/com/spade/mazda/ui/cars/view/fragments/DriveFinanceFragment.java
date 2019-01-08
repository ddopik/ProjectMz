package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.presenter.DriveFinancePresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.DriveFinancePresenter;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public class DriveFinanceFragment extends BaseFragment implements DriveFinanceView, View.OnClickListener {

    private DriveFinancePresenter driveFinancePresenter;
    private View driveFinanceView;
    private ProgressBar progressBar;
    private CustomTextView monthlyInstallmentValue, originalPrice;
    private RelativeLayout installmentLayout;
    private CustomEditText carModelEditText, carYearsEditText,
            carTrimsEditText, programEditText, NumberOfYearsEditText, downPaymentEditText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        driveFinanceView = inflater.inflate(R.layout.fragment_drive_finance, container, false);
        initViews();
        return driveFinanceView;
    }

    @Override
    protected void initPresenter() {
        driveFinancePresenter = new DriveFinancePresenterImpl(getContext(), getChildFragmentManager());
        driveFinancePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomButton calculateBtn = driveFinanceView.findViewById(R.id.calculate_btn);
        CustomTextView interestedBtn = driveFinanceView.findViewById(R.id.interested_btn);

        originalPrice = driveFinanceView.findViewById(R.id.original_price);
        carModelEditText = driveFinanceView.findViewById(R.id.car_model_edit_text);
        carYearsEditText = driveFinanceView.findViewById(R.id.car_year_edit_text);
        carTrimsEditText = driveFinanceView.findViewById(R.id.car_trim_edit_text);
        NumberOfYearsEditText = driveFinanceView.findViewById(R.id.installment_years_edit_text);
        programEditText = driveFinanceView.findViewById(R.id.program_edit_text);
        downPaymentEditText = driveFinanceView.findViewById(R.id.down_payment_edit_text);
        monthlyInstallmentValue = driveFinanceView.findViewById(R.id.monthly_installment);
        installmentLayout = driveFinanceView.findViewById(R.id.installment_layout);
        progressBar = driveFinanceView.findViewById(R.id.progress_bar);

        carModelEditText.setOnClickListener(this);
        carYearsEditText.setOnClickListener(this);
        carTrimsEditText.setOnClickListener(this);
        NumberOfYearsEditText.setOnClickListener(this);
        programEditText.setOnClickListener(this);
        calculateBtn.setOnClickListener(this);
        interestedBtn.setOnClickListener(this);

        int carId = getArguments().getInt(CarDetailsFragment.EXTRA_CAR_ID);
        driveFinancePresenter.getCarDetails(carId);
        driveFinancePresenter.getPrograms();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int resID) {
        Toast.makeText(getContext(), getString(resID), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setError(EditText editText, int resId) {
        editText.setError(getString(resId));
    }

    @Override
    public void showMonthlyInstallment(String value) {
        installmentLayout.setVisibility(View.VISIBLE);
        monthlyInstallmentValue.setText(value);
    }

    @Override
    public void setCarModel(String carModel) {
        carModelEditText.setText(carModel);
    }

    @Override
    public void setCarYear(String carYear) {
        carYearsEditText.setText(carYear);
    }

    @Override
    public void setCarTrim(String carTrim) {
        carTrimsEditText.setText(carTrim);
    }

    @Override
    public void setSelectedProgram(String selectedProgram) {
        programEditText.setText(selectedProgram);
    }

    @Override
    public void setNumberOfYears(String numberOfYears) {
        NumberOfYearsEditText.setText(numberOfYears);
    }

    @Override
    public void setCarModelError() {
        carModelEditText.setError(getString(R.string.please_choose_car_model));
    }

    @Override
    public void setCarYearError() {
        carYearsEditText.setError(getString(R.string.please_choose_car_year));

    }

    @Override
    public void setCarTrimError() {
        carTrimsEditText.setError(getString(R.string.please_choose_car_trim));

    }

    @Override
    public void setProgramError() {
        programEditText.setError(getString(R.string.choose_program));
    }

    @Override
    public void setNumberOfYearsError() {
        NumberOfYearsEditText.setError(getString(R.string.choose_number_of_years));
    }

    @Override
    public void setDownPaymentError(int resID) {
        downPaymentEditText.setError(getString(resID));
    }

    @Override
    public void setOriginalPrice(String carPrice) {
        originalPrice.setText(String.format(getString(R.string.currency), carPrice));
    }

    @Override
    public void showProgressDialog() {
        showMazdaProgressDialog(R.string.loading);
    }

    @Override
    public void hideProgressDialog() {
        hideMazdaProgressDialog();
    }

    @Override
    public void showSuccessDialog() {
        showConfirmationDialog(R.string.contact_soon);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculate_btn:
                if (driveFinancePresenter.dataIsValid(downPaymentEditText.getText().toString())) {
                    driveFinancePresenter.calculateMonthlyInstallment();
                }
                break;
            case R.id.car_model_edit_text:
                driveFinancePresenter.showCarModelsDialog();
                break;
            case R.id.car_year_edit_text:
                driveFinancePresenter.showCarYearsDialog();
                break;
            case R.id.car_trim_edit_text:
                driveFinancePresenter.showCarTrimsDialog();
                break;
            case R.id.program_edit_text:
                driveFinancePresenter.showProgramsDialog();
                break;
            case R.id.installment_years_edit_text:
                driveFinancePresenter.showYearsDialog();
                break;
            case R.id.interested_btn:
                if (driveFinancePresenter.dataIsValid(downPaymentEditText.getText().toString())) {
                    driveFinancePresenter.submitAsInterested();
                }
                break;
        }
    }
}
