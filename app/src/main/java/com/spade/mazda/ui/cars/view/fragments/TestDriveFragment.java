package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.presenter.TestDrivePresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.TestDrivePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class TestDriveFragment extends BaseFragment implements TestDriveView, View.OnClickListener {

    private View testDriveView;
    private MazdaProgressDialog progressDialog;
    private ProgressBar progressBar;
    private CustomEditText dateEditText, modelEditText, branchEditText, cityEditText;
    private TestDrivePresenter testDrivePresenter;


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
        CustomButton customButton = testDriveView.findViewById(R.id.request_btn);
        progressBar = testDriveView.findViewById(R.id.progress_bar);
        dateEditText = testDriveView.findViewById(R.id.date_edit_text);
        modelEditText = testDriveView.findViewById(R.id.car_model_edit_text);
        branchEditText = testDriveView.findViewById(R.id.branches_edit_text);
        cityEditText = testDriveView.findViewById(R.id.cities_edit_text);

        customButton.setOnClickListener(this);
        dateEditText.setOnClickListener(this);
        modelEditText.setOnClickListener(this);
        branchEditText.setOnClickListener(this);
        cityEditText.setOnClickListener(this);

        testDrivePresenter.getShowRooms();
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
    public void setCarModel(String carModel) {
        modelEditText.setText(carModel);
    }

    @Override
    public void setBranchName(String branchName) {
        branchEditText.setText(branchName);
    }

    @Override
    public void setDate(String date) {
        dateEditText.setText(date);
    }

    @Override
    public void setCity(String city) {
        cityEditText.setText(city);
    }

    @Override
    public void setCarModelError() {
        modelEditText.setError(getString(R.string.please_choose_car_model));
    }

    @Override
    public void setBranchError() {
        branchEditText.setError(getString(R.string.please_choose_nearest_showroom));
    }

    @Override
    public void setDateError() {
        dateEditText.setError(getString(R.string.pick_date));
    }

    @Override
    public void setCityError() {
        cityEditText.setError(getString(R.string.please_choose_city));
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.car_model_edit_text:
                testDrivePresenter.showCarModelsDialog(getChildFragmentManager());
                break;
            case R.id.cities_edit_text:
                testDrivePresenter.showCitiesDialog(getChildFragmentManager());
                break;
            case R.id.branches_edit_text:
                testDrivePresenter.showBranchesDialog(getChildFragmentManager());
                break;
            case R.id.date_edit_text:
                testDrivePresenter.showDatePicker(getChildFragmentManager());
                break;
            case R.id.request_btn:
                if (testDrivePresenter.dataIsValid()) {
                    testDrivePresenter.requestTest();
                }
                break;
        }
    }

}
