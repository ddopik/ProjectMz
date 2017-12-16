package com.spade.mazda.ui.services.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.general.view.CarModelsSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarTrimSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarYearsSpinnerAdapter;
import com.spade.mazda.ui.general.view.LoginDialogFragment;
import com.spade.mazda.ui.services.model.SparePartCategory;
import com.spade.mazda.ui.services.presenter.SparePartsPresenter;
import com.spade.mazda.ui.services.presenter.SparePartsPresenterImpl;
import com.spade.mazda.ui.services.view.activities.RequestSparePartsActivity;
import com.spade.mazda.ui.services.view.adapters.SparePartsCategoriesAdapter;
import com.spade.mazda.ui.services.view.interfaces.SparePartsView;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsFragment extends BaseFragment implements SparePartsView {

    private SparePartsPresenter sparePartsPresenter;
    private AppCompatSpinner carModelSpinner, carYearsSpinner, carTrimsSpinner;
    private View sparePartsView;
    private ProgressBar progressBar;

    private SparePartsCategoriesAdapter sparePartsCategoriesAdapter;
    private CarModelsSpinnerAdapter carModelsSpinnerAdapter;
    private CarYearsSpinnerAdapter carYearsSpinnerAdapter;
    private CarTrimSpinnerAdapter carTrimSpinnerAdapter;

    private List<SparePartCategory> sparePartCategories = new ArrayList<>();
    private List<CarModel> carModelList = new ArrayList<>();
    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();

    private int trimId = -1;
    private String filterString;
    //    private int modelId = -1, yearId = -1, trimId = -1, colorId = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sparePartsView = inflater.inflate(R.layout.fragment_spare_parts, container, false);
        initViews();
        return sparePartsView;
    }

    @Override
    protected void initPresenter() {
        sparePartsPresenter = new SparePartsPresenterImpl(getContext());
        sparePartsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        carModelSpinner = sparePartsView.findViewById(R.id.models_spinner);
        carYearsSpinner = sparePartsView.findViewById(R.id.years_spinner);
        carTrimsSpinner = sparePartsView.findViewById(R.id.trims_spinner);
        progressBar = sparePartsView.findViewById(R.id.progress_bar);

        TextView requestSparePartBtn = sparePartsView.findViewById(R.id.request_spare_part_button);
        LinearLayout filterLayout = sparePartsView.findViewById(R.id.filter_layout);
        CustomRecyclerView sparePartsRecycler = sparePartsView.findViewById(R.id.spare_parts_recycler_view);
        TextView doneTextView = sparePartsView.findViewById(R.id.done_btn);
        CustomTextView filterText = sparePartsView.findViewById(R.id.filter_text_view);

        carModelsSpinnerAdapter = new CarModelsSpinnerAdapter(carModelList, getContext());
        carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
        carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());

        carModelSpinner.setAdapter(carModelsSpinnerAdapter);
        carYearsSpinner.setAdapter(carYearsSpinnerAdapter);
        carTrimsSpinner.setAdapter(carTrimSpinnerAdapter);

        setUpCarsSpinners();

        sparePartsCategoriesAdapter = new SparePartsCategoriesAdapter(getContext(), sparePartCategories);
        sparePartsRecycler.setAdapter(sparePartsCategoriesAdapter);


        filterText.setOnClickListener(view -> filterLayout.setVisibility(View.VISIBLE));
        doneTextView.setOnClickListener(view -> {
            filterLayout.setVisibility(View.GONE);
            Log.d("TRIM_ID", String.valueOf(((ModelTrim) carTrimsSpinner.getSelectedItem()).getTrimId()));
            sparePartsPresenter.getSpareParts(String.valueOf(trimId));
        });

        requestSparePartBtn.setOnClickListener(view -> navigateToRequest());
        sparePartsPresenter.getSpareParts(null);
        sparePartsPresenter.getCarModels();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showSparParts(List<SparePartCategory> sparePartCategories) {
        this.sparePartCategories.clear();
        this.sparePartCategories.addAll(sparePartCategories);
        sparePartsCategoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCarModels(List<CarModel> carModels) {
        this.carModelList.clear();
        this.carModelList.addAll(carModels);
        carModelsSpinnerAdapter.notifyDataSetChanged();
    }

    private void setUpCarsSpinners() {
        carModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel carModel = carModelList.get(i);
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


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void navigateToRequest() {
        if (PrefUtils.isLoggedIn(getContext())) {
            startActivity(RequestSparePartsActivity.getLaunchIntent(getContext()));
        } else {
            showLoginDialog();
        }
    }

    private void showLoginDialog() {
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(getChildFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }
}
