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
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.presenter.TestDrivePresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.TestDrivePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.find_us.view.adapters.CitySpinnerAdapter;
import com.spade.mazda.ui.general.view.BranchesSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarModelsSpinnerAdapter;
import com.spade.mazda.ui.general.view.MazdaProgressDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class TestDriveFragment extends BaseFragment implements TestDriveView, PickDateDialog.OnDateSet {

    private View testDriveView;
    private MazdaProgressDialog progressDialog;
    private ProgressBar progressBar;
    private AppCompatSpinner carModelSpinner;
    private CustomEditText dateEditText;

    private CarModelsSpinnerAdapter carModelsSpinnerAdapter;
    private CitySpinnerAdapter citySpinnerAdapter;
    private BranchesSpinnerAdapter branchesSpinnerAdapter;

    private List<CarModel> carModelList = new ArrayList<>();
    private List<Branch> showroomList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();

    private TestDrivePresenter testDrivePresenter;

    private String dateString;
    private int modelId = -1, branchId = -1, cityID = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        testDriveView = inflater.inflate(R.layout.fragment_test_drive, container, false);
        return testDriveView;
    }

    @Override
    protected void initPresenter() {
        testDrivePresenter = new TestDrivePresenterImpl(getContext());
        testDrivePresenter.setView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected void initViews() {
        AppCompatSpinner showroomSpinner = testDriveView.findViewById(R.id.branches_spinner);
        AppCompatSpinner citiesSpinner = testDriveView.findViewById(R.id.cities_spinner);

        CustomButton customButton = testDriveView.findViewById(R.id.request_btn);
        progressBar = testDriveView.findViewById(R.id.progress_bar);
        dateEditText = testDriveView.findViewById(R.id.date_edit_text);
        carModelSpinner = testDriveView.findViewById(R.id.car_model_spinner);

        dateEditText.setFocusable(false);
        dateEditText.setClickable(true);

        carModelsSpinnerAdapter = new CarModelsSpinnerAdapter(carModelList, getContext());
        branchesSpinnerAdapter = new BranchesSpinnerAdapter(showroomList, getContext());
        citySpinnerAdapter = new CitySpinnerAdapter(cityList, getContext());

        carModelSpinner.setAdapter(carModelsSpinnerAdapter);
        showroomSpinner.setAdapter(branchesSpinnerAdapter);
        citiesSpinner.setAdapter(citySpinnerAdapter);

        setUpCarsSpinners();
        testDrivePresenter.getCarModels();
        testDrivePresenter.getShowRooms();

        dateEditText.setOnClickListener(view1 -> showDatePicker());
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

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                City city = cityList.get(i);
                cityID = city.getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean dataIsValid() {
        dateString = dateEditText.getText().toString();
        if (modelId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_model), Toast.LENGTH_LONG).show();
            return false;
        }

        if (branchId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_nearest_showroom), Toast.LENGTH_LONG).show();
            return false;
        }

        if (cityID == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_city), Toast.LENGTH_LONG).show();
            return false;
        }

        if (dateString.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.pick_date), Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    private void proceed() {
        testDrivePresenter.requestTest(
                String.valueOf(modelId),
                String.valueOf(cityID),
                String.valueOf(branchId),
                dateString);
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
        testDrivePresenter.getCities(branches);
    }

    @Override
    public void showCities(List<City> cityList) {
        this.cityList.clear();
        this.cityList.addAll(cityList);
        this.citySpinnerAdapter.notifyDataSetChanged();
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
    public void showConfirmationDialog() {
        showConfirmationDialog(R.string.thanks_for_reserving);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    private void showDatePicker() {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(getChildFragmentManager(), PickDateDialog.class.getSimpleName());
    }


    @Override
    public void onDateSet(int year, int month, int day) {
        dateString = year + "-" + (month + 1) + "-" + day;
        dateEditText.setText(dateString);
    }


    private void setUpCarsSpinners() {
        carModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel carModel = carModelList.get(i);
                modelId = carModel.getCarModelId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
