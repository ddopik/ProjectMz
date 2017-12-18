package com.spade.mazda.ui.cars.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.presenter.CarDetailsPresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.CarDetailsPresenter;
import com.spade.mazda.ui.cars.view.activity.DriveFinanceActivity;
import com.spade.mazda.ui.cars.view.adapter.PagingAdapter;
import com.spade.mazda.ui.cars.view.interfaces.CarDetailsView;
import com.spade.mazda.ui.general.view.CarTrimSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarYearsSpinnerAdapter;
import com.spade.mazda.utils.GlideApp;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarDetailsFragment extends BaseFragment implements CarDetailsView {

    public static final String EXTRA_CAR_ID = "EXTRA_CAR_ID";
    public static final String EXTRA_TRIM_POSITION = "EXTRA_TRIM_POSITION";
    public static final String EXTRA_YEAR_POSITION = "EXTRA_YEAR_POSITION";

    public static final String EXTRA_CAR_YEARS = "EXTRA_CAR_YEARS";

    private CarDetailsPresenter carDetailsPresenter;
    private View carDetailsView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView carModelName;
    private ImageView carImageView;
    private AppCompatSpinner yearsSpinner, trimsSpinner;
    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();

    private PagingAdapter pagingAdapter;
    private CarYearsSpinnerAdapter carYearsSpinnerAdapter;
    private CarTrimSpinnerAdapter carTrimSpinnerAdapter;

    private String carId, trimId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carDetailsView = inflater.inflate(R.layout.fragment_car_details, container, false);
        initViews();
        return carDetailsView;
    }

    @Override
    protected void initPresenter() {
        carDetailsPresenter = new CarDetailsPresenterImpl(getContext());
        carDetailsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        yearsSpinner = carDetailsView.findViewById(R.id.years_spinner);
        trimsSpinner = carDetailsView.findViewById(R.id.trims_spinner);
        viewPager = carDetailsView.findViewById(R.id.fragments_viewpager);
        tabLayout = carDetailsView.findViewById(R.id.tabs);
        carImageView = carDetailsView.findViewById(R.id.car_image);
        carModelName = carDetailsView.findViewById(R.id.model_name);
        pagingAdapter = new PagingAdapter(getChildFragmentManager());
        carDetailsPresenter.setUpViewPagerFragments();


        carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
        carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());

        trimsSpinner.setAdapter(carTrimSpinnerAdapter);
        yearsSpinner.setAdapter(carYearsSpinnerAdapter);

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
                trimId = String.valueOf(modelTrim.getTrimId());
                carDetailsPresenter.getCarDetails(PrefUtils.getAppLang(getContext()), carId, trimId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        carId = getArguments().getString(EXTRA_CAR_ID);
        carDetailsPresenter.getCarModel(Integer.parseInt(carId));
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showCarDetails() {

    }

    @Override
    public void showCarYearsAndTrims(List<CarYear> carYears) {
        this.carYears.clear();
        this.carYears.addAll(carYears);
        carYearsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles) {
        pagingAdapter.addFragment(fragmentList, fragmentTitles);
        viewPager.setAdapter(pagingAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void updateUI(String modelImage, String Name) {
        GlideApp.with(getContext()).load(modelImage).into(carImageView);
        carModelName.setText(Name);
    }

    @Override
    public void navigateToCalculator() {
        Intent intent = DriveFinanceActivity.getLaunchIntent(getContext());
        intent.putExtra(EXTRA_CAR_ID, Integer.parseInt(carId));
        startActivity(intent);
    }

//    @Override
//    public void navigateToBook() {
//        Intent intent = BookCarActivity.getLaunchIntent(getContext());
//        intent.putExtra(EXTRA_CAR_ID, Integer.parseInt(carId));
//        intent.putExtra(EXTRA_YEAR_POSITION, yearsSpinner.getSelectedItemPosition());
//        intent.putExtra(EXTRA_TRIM_POSITION, trimsSpinner.getSelectedItemPosition());
//        startActivity(intent);
//    }

}
