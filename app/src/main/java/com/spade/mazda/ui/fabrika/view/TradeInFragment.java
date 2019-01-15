package com.spade.mazda.ui.fabrika.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.fabrika.model.FabricaData;
import com.spade.mazda.ui.fabrika.presenter.TradeInPresenter;
import com.spade.mazda.ui.fabrika.presenter.TradeInPresenterImpl;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.utils.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class TradeInFragment extends BaseFragment implements TradeInView {

    private TradeInPresenter tradeInPresenter;
    private View tradeInView;
    private ProgressBar progressBar;
    private MazdaProgressDialog progressDialog;
    private EditText nameEditText, emailEditText, mobileNumberEditText;
    private FabricaSpinnerAdapter branchesSpinnerAdapter, brandsSpinnerAdapter, modelsSpinnerAdapter;
    private List<FabricaData> carBrands = new ArrayList<>();
    private List<FabricaData> carModels = new ArrayList<>();
    private List<FabricaData> branches = new ArrayList<>();
    private String nameString, emailString, mobileNumberString;
    private int myCarBrandId, myCarModelId, requestedCarBrandId, requestedCarModelId, branchId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tradeInView = inflater.inflate(R.layout.fragment_trade_in, container, false);
        initViews();
        return tradeInView;
    }

    @Override
    protected void initPresenter() {
        tradeInPresenter = new TradeInPresenterImpl(getContext());
        tradeInPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        AppCompatSpinner myCarBrandSpinner = tradeInView.findViewById(R.id.my_car_brand_spinner);
        AppCompatSpinner myCarModelSpinner = tradeInView.findViewById(R.id.my_car_model_spinner);
        AppCompatSpinner requestedCarBrandSpinner = tradeInView.findViewById(R.id.requested_car_brand_spinner);
        AppCompatSpinner requestedCarModelSpinner = tradeInView.findViewById(R.id.requested_car_model_spinner);
        AppCompatSpinner branchesSpinner = tradeInView.findViewById(R.id.branches_spinner);
        CustomButton requestBtn = tradeInView.findViewById(R.id.request_btn);

        progressBar = tradeInView.findViewById(R.id.progress_bar);
        nameEditText = tradeInView.findViewById(R.id.name_edit_text);
        emailEditText = tradeInView.findViewById(R.id.email_edit_text);
        mobileNumberEditText = tradeInView.findViewById(R.id.mobile_edit_text);

        brandsSpinnerAdapter = new FabricaSpinnerAdapter(carBrands, getContext(), true);
        modelsSpinnerAdapter = new FabricaSpinnerAdapter(carModels, getContext(), false);
        branchesSpinnerAdapter = new FabricaSpinnerAdapter(branches, getContext(), false);

        myCarBrandSpinner.setAdapter(brandsSpinnerAdapter);
        requestedCarBrandSpinner.setAdapter(brandsSpinnerAdapter);

        myCarModelSpinner.setAdapter(modelsSpinnerAdapter);
        requestedCarModelSpinner.setAdapter(modelsSpinnerAdapter);

        branchesSpinner.setAdapter(branchesSpinnerAdapter);

        tradeInPresenter.getBrands();
        tradeInPresenter.getModels();
        tradeInPresenter.getBranches();
        myCarBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FabricaData fabricaData = carBrands.get(i);
                myCarBrandId = fabricaData.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        myCarModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FabricaData fabricaData = carModels.get(i);
                myCarModelId = fabricaData.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        requestedCarBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FabricaData fabricaData = carBrands.get(i);
                requestedCarBrandId = fabricaData.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        requestedCarModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FabricaData fabricaData = carModels.get(i);
                requestedCarModelId = fabricaData.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        branchesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FabricaData fabricaData = branches.get(i);
                branchId = fabricaData.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        requestBtn.setOnClickListener(view -> {
            if (dataIsValid()) {
                proceed();
            }
        });
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

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
    public void showBrands(List<FabricaData> brandsList) {
        carBrands.clear();
        carBrands.addAll(brandsList);
        brandsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showModels(List<FabricaData> modelsList) {
        carModels.clear();
        carModels.addAll(modelsList);
        modelsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBranches(List<FabricaData> branchesList) {
        branches.clear();
        branches.addAll(branchesList);
        branchesSpinnerAdapter.notifyDataSetChanged();
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

    private boolean dataIsValid() {
        nameString = nameEditText.getText().toString();
        emailString = emailEditText.getText().toString();
        mobileNumberString = mobileNumberEditText.getText().toString();

        if (nameString == null || nameString.isEmpty()) {
            nameEditText.setError(getString(R.string.enter_user_name));
            return false;
        }

        if (emailString == null || emailString.isEmpty()) {
            emailEditText.setError(getString(R.string.enter_email_address));
            return false;
        } else if (!Validator.isEmailAddressValid(emailString)) {
            emailEditText.setError(getString(R.string.enter_valid_email_address));
            return false;
        }
        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            mobileNumberEditText.setError(getString(R.string.enter_phone_number));
            return false;
        }
        return true;
    }

    private void proceed() {
        tradeInPresenter.sendRequest(nameString, emailString, mobileNumberString, myCarBrandId, myCarModelId, requestedCarBrandId, requestedCarModelId, branchId);
    }
}
