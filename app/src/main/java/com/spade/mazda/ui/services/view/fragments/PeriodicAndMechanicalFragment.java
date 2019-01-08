package com.spade.mazda.ui.services.view.fragments;

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

import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.spinners.BranchesSpinnerAdapter;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.services.model.Kilometer;
import com.spade.mazda.ui.services.presenter.PeriodicServicePresenter;
import com.spade.mazda.ui.services.presenter.PeriodicServicesPresenterImpl;
import com.spade.mazda.ui.services.view.adapters.KilometersSpinnerAdapter;
import com.spade.mazda.ui.services.view.interfaces.PeriodicView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class PeriodicAndMechanicalFragment extends BaseFragment implements PeriodicView {

    private View periodicView;
    private BranchesSpinnerAdapter branchesSpinnerAdapter;
    private KilometersSpinnerAdapter kilometersSpinnerAdapter;
    private PeriodicServicePresenter periodicServicePresenter;
    private List<Branch> serviceCenters = new ArrayList<>();
    private List<Kilometer> kilometers = new ArrayList<>();
    private MazdaProgressDialog progressDialog;
    private ProgressBar progressBar;
    private int kilometerID, branchID;
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
        AppCompatSpinner kilometersSpinner = periodicView.findViewById(R.id.kilometers_spinner);
        AppCompatSpinner serviceCentersSpinner = periodicView.findViewById(R.id.branches_spinner);

        dateEditText = periodicView.findViewById(R.id.date_edit_text);
        dateEditText.setFocusable(false);
        dateEditText.setClickable(true);

        progressBar = periodicView.findViewById(R.id.progress_bar);
        branchesSpinnerAdapter = new BranchesSpinnerAdapter(serviceCenters, getContext());
        kilometersSpinnerAdapter = new KilometersSpinnerAdapter(kilometers, getContext());

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

        periodicServicePresenter.getKiloMetersServices();
        periodicServicePresenter.getServiceCenters();
    }

    private boolean dataIsValid() {

        return true;
    }

}
