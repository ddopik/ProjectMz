package com.spade.mazda.ui.services.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.general.view.spinners.BranchesSpinnerAdapter;
import com.spade.mazda.ui.services.model.AvailableTimeData;
import com.spade.mazda.ui.services.model.Kilometer;
import com.spade.mazda.ui.services.presenter.PeriodicServicePresenter;
import com.spade.mazda.ui.services.presenter.PeriodicServicesPresenterImpl;
import com.spade.mazda.ui.services.view.adapters.AvailaibleTimesAdapter;
import com.spade.mazda.ui.services.view.adapters.KilometersSpinnerAdapter;
import com.spade.mazda.ui.services.view.interfaces.PeriodicView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class PeriodicAndMechanicalFragment extends BaseFragment implements PeriodicView {

    private View periodicView;
    private TextInputLayout availableTimesDateInput;
    private BranchesSpinnerAdapter branchesSpinnerAdapter;
    private KilometersSpinnerAdapter kilometersSpinnerAdapter;
    private PeriodicServicePresenter periodicServicePresenter;
    private List<Branch> serviceCenters = new ArrayList<>();
    private List<Kilometer> kilometers = new ArrayList<>();

    private AvailaibleTimesAdapter availaibleTimesAdapter;
    private List<AvailableTimeData> availableTimeDataList = new ArrayList<>();
    AppCompatSpinner kilometersSpinner;
    AppCompatSpinner serviceCentersSpinner;
    private MazdaProgressDialog progressDialog;
    private ProgressBar progressBar;
    private CustomButton reserveBtn;
    private int kilometerID, branchID;
    private int availableTimeId;
    private CustomEditText dateEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        periodicView = inflater.inflate(R.layout.fragment_periodic, container, false);
        initViews();
        return periodicView;
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
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showKilometers(List<Kilometer> kilometers) {
        this.kilometers.clear();
        this.kilometers.addAll(kilometers);
        kilometersSpinnerAdapter.notifyDataSetChanged();

    }

    @Override
    public void showServiceCenters(List<Branch> branches) {
        serviceCenters.clear();
        serviceCenters.addAll(branches);
        branchesSpinnerAdapter.notifyDataSetChanged();

    }

    @Override
    public void setDate(String date) {
        dateEditText.setText(date);
    }

    @Override
    protected void initPresenter() {
        periodicServicePresenter = new PeriodicServicesPresenterImpl(getContext());
        periodicServicePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        kilometersSpinner = periodicView.findViewById(R.id.kilometers_spinner);
        serviceCentersSpinner = periodicView.findViewById(R.id.branches_spinner);

        dateEditText = periodicView.findViewById(R.id.date_edit_text);
        dateEditText.setFocusable(false);
        dateEditText.setClickable(true);
        availableTimesDateInput = periodicView.findViewById(R.id.available_times_date_input);
        progressBar = periodicView.findViewById(R.id.progress_bar);
        branchesSpinnerAdapter = new BranchesSpinnerAdapter(serviceCenters, getContext());
        kilometersSpinnerAdapter = new KilometersSpinnerAdapter(kilometers, getContext());
        reserveBtn = periodicView.findViewById(R.id.reserve_btn);
        kilometersSpinner.setAdapter(kilometersSpinnerAdapter);
        serviceCentersSpinner.setAdapter(branchesSpinnerAdapter);

        kilometersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Kilometer kilometer = kilometers.get(i);
                kilometerID = kilometer.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        serviceCentersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Branch branch = serviceCenters.get(i);
                branchID = branch.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reserveBtn.setOnClickListener(v -> {
            if (dataIsValid()) {
                periodicServicePresenter.submitPeriodicalData(branchID, kilometerID, availableTimeId);
            }
        });

        dateEditText.setOnClickListener(v -> periodicServicePresenter.openDatePicker(getFragmentManager()));


        CustomRecyclerView tagsRv = periodicView.findViewById(R.id.available_times_rv);

        // Create the FlexboxLayoutMananger, only flexbox library version 0.3.0 or higher support.
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);

        // Set JustifyContent.
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        tagsRv.setLayoutManager(flexboxLayoutManager);

        // Set adapter object.

        availaibleTimesAdapter = new AvailaibleTimesAdapter(availableTimeDataList);

        tagsRv.setAdapter(availaibleTimesAdapter);


        availaibleTimesAdapter.onSelectedItemClicked = selectedAvailableTimeData -> {
            for (int i = 0; i < availableTimeDataList.size(); i++) {
                if (availableTimeDataList.get(i).id.equals(selectedAvailableTimeData.id)) {
                    availableTimeDataList.get(i).isSelected = !availableTimeDataList.get(i).isSelected;
                } else {
                    availableTimeDataList.get(i).isSelected = false;
                }
                availaibleTimesAdapter.notifyDataSetChanged();
            }

        };

        periodicServicePresenter.getAvailableTimes();
        periodicServicePresenter.getKiloMetersServices();
        periodicServicePresenter.getServiceCenters();
    }


    @Override
    public void showAvailableTimes(List<AvailableTimeData> availableTimeDataList) {

        this.availableTimeDataList.clear();
        this.availableTimeDataList.addAll(availableTimeDataList);
        availaibleTimesAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewMessage(String msg) {
        showMessage(msg);
    }

    private boolean dataIsValid() {

        boolean validateState = true;

        Kilometer defaultKilometer = (Kilometer) kilometersSpinner.getSelectedItem();
        if(defaultKilometer !=null)
        kilometerID = defaultKilometer.getId();

        Branch defaultBranch = (Branch) serviceCentersSpinner.getSelectedItem();
        if(defaultBranch !=null)
        branchID = defaultBranch.getId();

        if (branchID == 0) {
            showMessage(getResources().getString(R.string.please_select_service_location));
            validateState = false;
        }
        if (kilometerID == 0) {
            showMessage(getResources().getString(R.string.please_select_service_type));
            validateState = false;
        }


        if (dateEditText.getText().length() == 0) {
            availableTimesDateInput.setError(getResources().getString(R.string.plase_select_service_date));
            validateState = false;
        } else {
            availableTimesDateInput.setErrorEnabled(false);
        }


        for (AvailableTimeData selectedAvailableTimeData : availableTimeDataList) {
            if (selectedAvailableTimeData.isSelected) {
                this.availableTimeId = selectedAvailableTimeData.id;
            }
        }

        if (availableTimeId == 0) {
            showMessage(getResources().getString(R.string.please_select_availaible_time));
            validateState = false;
        }


        return validateState;
    }

}
