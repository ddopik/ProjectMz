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

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.general.view.spinners.BranchesSpinnerAdapter;
import com.spade.mazda.ui.general.view.SparePartsCategoriesSpinnerAdapter;
import com.spade.mazda.ui.general.view.SparePartsSpinnerAdapter;
import com.spade.mazda.ui.services.model.SparePart;
import com.spade.mazda.ui.services.model.SparePartCategory;
import com.spade.mazda.ui.services.presenter.RequestSparePartPresenterImpl;
import com.spade.mazda.ui.services.presenter.RequestSparePartsPresenter;
import com.spade.mazda.ui.services.view.interfaces.RequestSparePartsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/15/17.
 */

public class RequestSparePartsFragment extends BaseFragment implements RequestSparePartsView {

    private View requestSparePartsView;
    private AppCompatSpinner branchSpinner, sparePartsCategoriesSpinner, sparePartsSpinner;
    private ProgressBar progressBar;
    private CustomEditText descriptionEditText;
    private RequestSparePartsPresenter requestSparePartsPresenter;

    private SparePartsCategoriesSpinnerAdapter sparePartsCategoriesSpinnerAdapter;
    private BranchesSpinnerAdapter branchesSpinnerAdapter;
    private SparePartsSpinnerAdapter sparePartsSpinnerAdapter;

    private List<SparePart> spareParts = new ArrayList<>();
    private List<Branch> outLetsList = new ArrayList<>();
    private List<SparePartCategory> sparePartCategories = new ArrayList<>();

    private int branchID = -1, sparePartID = -1, sparePartTypeID = -1;
    private String sparePartDescription = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        requestSparePartsView = inflater.inflate(R.layout.fragment_request_spare_part, container, false);
        initViews();
        return requestSparePartsView;
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
    public void showSparePartsCategories(List<SparePartCategory> sparePartCategories) {
        this.sparePartCategories.clear();
        this.sparePartCategories.addAll(sparePartCategories);
        sparePartsCategoriesSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSparePartsOutLets(List<Branch> sparePartsOutlets) {
        this.outLetsList.clear();
        this.outLetsList.addAll(sparePartsOutlets);
        branchesSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showConfirmationDialog() {
        showConfirmationDialog(R.string.thanks_for_reserving);
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
    public void finish() {
        getActivity().finish();
    }

    @Override
    protected void initPresenter() {
        requestSparePartsPresenter = new RequestSparePartPresenterImpl(getContext());
        requestSparePartsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomButton sendRequest = requestSparePartsView.findViewById(R.id.send_request);
        progressBar = requestSparePartsView.findViewById(R.id.progress_bar);
        branchSpinner = requestSparePartsView.findViewById(R.id.outlets_spinner);
        sparePartsCategoriesSpinner = requestSparePartsView.findViewById(R.id.spare_part_types_spinner);
        sparePartsSpinner = requestSparePartsView.findViewById(R.id.spare_parts_spinner);
        descriptionEditText = requestSparePartsView.findViewById(R.id.description_edit_text);
        branchesSpinnerAdapter = new BranchesSpinnerAdapter(outLetsList, getContext());
        sparePartsCategoriesSpinnerAdapter = new SparePartsCategoriesSpinnerAdapter(sparePartCategories, getContext());

        branchSpinner.setAdapter(branchesSpinnerAdapter);
        sparePartsCategoriesSpinner.setAdapter(sparePartsCategoriesSpinnerAdapter);

        setUpSinners();

        requestSparePartsPresenter.getSparePartsOutlets(String.valueOf(FindUsFragment.SPARE_PARTS_TYPE));
        requestSparePartsPresenter.getSpareParts();
        sendRequest.setOnClickListener(view -> {
            if (dataIsValid()) {
                proceed();
            }
        });
    }

    private void setUpSinners() {
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Branch branch = outLetsList.get(i);
                branchID = branch.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sparePartsCategoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SparePartCategory sparePartCategory = sparePartCategories.get(i);
                sparePartTypeID = sparePartCategory.getCategoryId();
                spareParts.clear();
                spareParts.addAll(sparePartCategory.getSparePartList());
                sparePartsSpinnerAdapter = new SparePartsSpinnerAdapter(spareParts, getContext());
                sparePartsSpinner.setAdapter(sparePartsSpinnerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sparePartsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SparePart sparePart = spareParts.get(i);
                sparePartID = sparePart.getSparePartId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean dataIsValid() {
        sparePartDescription = descriptionEditText.getText().toString();

        if (branchID == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_spare_part_outlet), Toast.LENGTH_LONG).show();
            return false;
        }


        if (sparePartTypeID == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_spare_part_type), Toast.LENGTH_LONG).show();
            return false;
        }

        if (sparePartID == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_spare_part), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void proceed() {
        requestSparePartsPresenter.requestSparePart(String.valueOf(sparePartTypeID),
                String.valueOf(sparePartID),
                String.valueOf(branchID),
                sparePartDescription);
    }
}
