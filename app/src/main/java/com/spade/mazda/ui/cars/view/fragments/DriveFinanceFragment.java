package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.presenter.DriveFinancePresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.DriveFinancePresenter;
import com.spade.mazda.ui.cars.view.adapter.ProgramsSpinnerAdapter;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;
import com.spade.mazda.ui.general.view.spinners.CarTrimSpinnerAdapter;
import com.spade.mazda.ui.general.view.spinners.CarYearsSpinnerAdapter;
import com.spade.mazda.ui.services.model.Program;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public class DriveFinanceFragment extends BaseFragment implements DriveFinanceView {

    private DriveFinancePresenter driveFinancePresenter;
    private View driveFinanceView;
    private AppCompatSpinner numberOfYearsSpinner;
    private ProgressBar progressBar;
    private CustomTextView monthlyInstallmentValue;
    private RelativeLayout installmentLayout;

    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();

    private List<Program> programs = new ArrayList<>();
    private ProgramsSpinnerAdapter programsSpinnerAdapter;
    private CarYearsSpinnerAdapter carYearsSpinnerAdapter;
    private CarTrimSpinnerAdapter carTrimSpinnerAdapter;
    private int carId, trimId, numberOfYears;
    private double downPayment;
    private String originalPriceValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        driveFinanceView = inflater.inflate(R.layout.fragment_drive_finance, container, false);
        initViews();
        return driveFinanceView;
    }

    @Override
    protected void initPresenter() {
        driveFinancePresenter = new DriveFinancePresenterImpl(getContext());
        driveFinancePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        AppCompatSpinner yearsSpinner = driveFinanceView.findViewById(R.id.car_year_spinner);
        AppCompatSpinner trimsSpinner = driveFinanceView.findViewById(R.id.car_trim_spinner);
        AppCompatSpinner programsSpinner = driveFinanceView.findViewById(R.id.programs_spinner);
        CustomTextView originalPrice = driveFinanceView.findViewById(R.id.original_price);
        CustomEditText downPaymentEditText = driveFinanceView.findViewById(R.id.down_payment_edit_text);
        CustomButton calculateBtn = driveFinanceView.findViewById(R.id.calculate_btn);
        numberOfYearsSpinner = driveFinanceView.findViewById(R.id.number_of_years_spinner);
        progressBar = driveFinanceView.findViewById(R.id.progress_bar);
        monthlyInstallmentValue = driveFinanceView.findViewById(R.id.monthly_installment);
        installmentLayout = driveFinanceView.findViewById(R.id.installment_layout);
        carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
        carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());
        programsSpinnerAdapter = new ProgramsSpinnerAdapter(programs, getContext());

        trimsSpinner.setAdapter(carTrimSpinnerAdapter);
        yearsSpinner.setAdapter(carYearsSpinnerAdapter);
        programsSpinner.setAdapter(programsSpinnerAdapter);

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarYear carYear = carYears.get(i);
                modelTrims.clear();
                modelTrims.addAll(carYear.getModelTrims());
                carTrimSpinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        trimsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ModelTrim modelTrim = modelTrims.get(i);
                trimId = modelTrim.getTrimId();
                originalPriceValue = modelTrim.getTrimPrice();
                originalPrice.setText(String.format(getString(R.string.currency), originalPriceValue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberOfYearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numberOfYears = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        programsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        calculateBtn.setOnClickListener(view -> {
            String downPayment = downPaymentEditText.getText().toString();
            if (!downPayment.isEmpty()) {
                boolean isValid = driveFinancePresenter.isDownPaymentValid(downPayment, Double.parseDouble(originalPriceValue));
                if (isValid) {
                    driveFinancePresenter.calculateMonthlyInstallment(Double.parseDouble(originalPriceValue), downPaymentEditText.getText().toString(), numberOfYears);
                }
            } else {
                setError(downPaymentEditText, R.string.enter_down_payment);
            }
        });

        carId = getArguments().getInt(CarDetailsFragment.EXTRA_CAR_ID);
        driveFinancePresenter.getCarYears(carId);
        driveFinancePresenter.getPrograms();
        showNumberOfYears();
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
    public void showCarYears(List<CarYear> carYears) {
        this.carYears.clear();
        this.carYears.addAll(carYears);
        this.carYearsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPrograms(List<Program> programs) {
        this.programs.clear();
        this.programs.addAll(programs);
        programsSpinnerAdapter.notifyDataSetChanged();
    }

    public void showNumberOfYears() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.number_of_years, R.layout.spinner_item);
        numberOfYearsSpinner.setAdapter(adapter);
    }

    @Override
    public void showMonthlyInstallment(String value) {
        installmentLayout.setVisibility(View.VISIBLE);
        monthlyInstallmentValue.setText(value);
    }

}
