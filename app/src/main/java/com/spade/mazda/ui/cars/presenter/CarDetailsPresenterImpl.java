package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.cars.model.CarDetailsData;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.presenter.interfaces.CarDetailsPresenter;
import com.spade.mazda.ui.cars.view.fragments.FragmentCarModelOverView;
import com.spade.mazda.ui.cars.view.fragments.FragmentCarModelSpecs;
import com.spade.mazda.ui.cars.view.interfaces.CarDetailsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarDetailsPresenterImpl implements CarDetailsPresenter {

    private Context context;
    private CarDetailsView carDetailsView;
    private FragmentCarModelOverView fragmentCarModelOverView;
    private FragmentCarModelSpecs fragmentCarModelSpecs;
    private DataSource dataSource;

    public CarDetailsPresenterImpl(Context context) {
        this.context = context;
        dataSource = DataSource.getInstance();
    }

    @Override
    public void setView(CarDetailsView view) {
        this.carDetailsView = view;
    }

    @Override
    public void getCarModel(int carID) {
        DataSource dataSource = DataSource.getInstance();
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> carDetailsView.showCarYearsAndTrims(carModel.getCarYears()));
    }

    @Override
    public void setUpViewPagerFragments() {
        fragmentCarModelOverView = new FragmentCarModelOverView();
        fragmentCarModelSpecs = new FragmentCarModelSpecs();
        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmentTitles = new ArrayList<>();

        fragments.add(fragmentCarModelOverView);
        fragments.add(fragmentCarModelSpecs);
        fragmentTitles.add(context.getString(R.string.overview));
        fragmentTitles.add(context.getString(R.string.specification));
        carDetailsView.addFragment(fragments, fragmentTitles);
    }

    @Override
    public void getCarTrims(CarYear carYear) {

    }

    @Override
    public void getCarDetails(String appLang, String carId, String trimId) {
        ApiHelper.getCarDetails(appLang, carId, trimId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carDetailsResponse -> {
                    CarDetailsData carDetailsData = carDetailsResponse.getCarDetailsData();
                    fragmentCarModelSpecs.updateSpecs(carDetailsData.getCategories());
                    fragmentCarModelOverView.updateUI(carDetailsData);
                    carDetailsView.updateUI(carDetailsData.getImage(), carDetailsData.getName());
                }, throwable -> {
                });
    }
}
