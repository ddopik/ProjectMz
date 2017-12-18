package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.presenter.interfaces.BookCarPresenter;
import com.spade.mazda.ui.cars.presenter.BookCarPresenterImpl;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.BranchesSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarModelsSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarTrimSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarYearsSpinnerAdapter;
import com.spade.mazda.ui.general.view.MazdaProgressDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class BookCarFragment extends BaseFragment implements BookCarView {

    private AppCompatSpinner carModelSpinner;
    private AppCompatSpinner carYearsSpinner;
    private AppCompatSpinner carTrimsSpinner;
    private CustomEditText nameEditText, mobileNumberEditText;
    private CarModelsSpinnerAdapter carModelsSpinnerAdapter;
    private CarYearsSpinnerAdapter carYearsSpinnerAdapter;
    private CarTrimSpinnerAdapter carTrimSpinnerAdapter;
    private BranchesSpinnerAdapter branchesSpinnerAdapter;

    private List<CarModel> carModelList = new ArrayList<>();
    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();
    private List<Branch> showroomList = new ArrayList<>();

    private BookCarPresenter bookCarPresenter;
    private View bookCarView;
    private ProgressBar progressBar;
    private MazdaProgressDialog progressDialog;
    private int modelId = -1, yearId = -1, trimId = -1, branchId = -1;
    //    private int yearPosition = 0, trimPosition = 0;
    private String nameString, phoneString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookCarView = inflater.inflate(R.layout.fragment_book_car, container, false);
        return bookCarView;
    }

    @Override
    protected void initPresenter() {
        bookCarPresenter = new BookCarPresenterImpl(getContext());
        bookCarPresenter.setView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected void initViews() {
        AppCompatSpinner showroomSpinner = bookCarView.findViewById(R.id.showroom_spinner);
        CustomButton customButton = bookCarView.findViewById(R.id.book_btn);
        progressBar = bookCarView.findViewById(R.id.progress_bar);
        nameEditText = bookCarView.findViewById(R.id.name_edit_text);
        mobileNumberEditText = bookCarView.findViewById(R.id.mobile_edit_text);

        carModelSpinner = bookCarView.findViewById(R.id.car_model_spinner);
        carYearsSpinner = bookCarView.findViewById(R.id.car_year_spinner);
        carTrimsSpinner = bookCarView.findViewById(R.id.car_trim_spinner);

        carModelsSpinnerAdapter = new CarModelsSpinnerAdapter(carModelList, getContext());
        carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
        carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());
        branchesSpinnerAdapter = new BranchesSpinnerAdapter(showroomList, getContext());

        carModelSpinner.setAdapter(carModelsSpinnerAdapter);
        carYearsSpinner.setAdapter(carYearsSpinnerAdapter);
        carTrimsSpinner.setAdapter(carTrimSpinnerAdapter);
        showroomSpinner.setAdapter(branchesSpinnerAdapter);

        setUpCarsSpinners();
        bookCarPresenter.getUser();
        bookCarPresenter.getCarModels();
        bookCarPresenter.getShowRooms();

        customButton.setOnClickListener(view -> {
            if (dataIsValid()) {
                proceed();
            }
        });

        showroomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Branch branch = showroomList.get(i);
                branchId = branch.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        modelId = getArguments().getInt(CarDetailsFragment.EXTRA_CAR_ID);
//        yearPosition = getArguments().getInt(CarDetailsFragment.EXTRA_YEAR_POSITION);
//        trimPosition = getArguments().getInt(CarDetailsFragment.EXTRA_TRIM_POSITION);
//
//        bookCarPresenter.getCarModel(modelId);

    }

    private boolean dataIsValid() {
        nameString = nameEditText.getText().toString();
        phoneString = mobileNumberEditText.getText().toString();

        if (nameString == null || nameString.isEmpty()) {
            nameEditText.setError(getString(R.string.enter_user_name));
            return false;
        }
        if (phoneString == null || phoneString.isEmpty()) {
            mobileNumberEditText.setError(getString(R.string.enter_phone_number));
            return false;
        }
        if (modelId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_model), Toast.LENGTH_LONG).show();
            return false;
        }


        if (yearId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_year), Toast.LENGTH_LONG).show();
            return false;
        }

        if (trimId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_trim), Toast.LENGTH_LONG).show();
            return false;
        }

        if (branchId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_nearest_showroom), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void proceed() {
        bookCarPresenter.bookCar(nameString,
                phoneString,
                String.valueOf(modelId),
                String.valueOf(yearId),
                String.valueOf((trimId)),
                String.valueOf(branchId));
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

    }

    @Override
    public void showCarModels(List<CarModel> carModels) {
        this.carModelList.clear();
        this.carModelList.addAll(carModels);
        this.carModelsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNearestShowRooms(List<Branch> branches) {
        this.showroomList.clear();
        this.showroomList.addAll(branches);
        this.branchesSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showName(String userName) {
        nameEditText.setText(userName);
    }

    @Override
    public void showMobileNumber(String mobileNumber) {
        mobileNumberEditText.setText(mobileNumber);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(R.string.loading);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }


    @Override
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setSelectedCar(CarModel selectedCar) {
        carYearsSpinner.setSelection(carModelList.indexOf(selectedCar));
    }

    @Override
    public void showConfirmationDialog() {
        showConfirmationDialog(R.string.thanks_for_reserving);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    private void setUpCarsSpinners() {
        carModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel carModel = carModelList.get(i);
                modelId = carModel.getCarModelId();
                carYears.clear();
                carYears.addAll(carModel.getCarYears());
                carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
                carYearsSpinner.setAdapter(carYearsSpinnerAdapter);
                setUpYearsSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpYearsSpinner() {
        carYearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarYear carYear = carYears.get(i);
                yearId = carYear.getYearID();
                modelTrims.clear();
                modelTrims.addAll(carYear.getModelTrims());
                carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());
                carTrimsSpinner.setAdapter(carTrimSpinnerAdapter);
                setUpTrimsSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpTrimsSpinner() {
        carTrimsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ModelTrim modelTrim = modelTrims.get(i);
                trimId = modelTrim.getTrimId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
