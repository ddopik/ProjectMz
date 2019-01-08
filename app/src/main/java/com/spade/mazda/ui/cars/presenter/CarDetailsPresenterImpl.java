package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.cars.model.CarDetailsData;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.presenter.interfaces.CarDetailsPresenter;
import com.spade.mazda.ui.cars.view.fragments.CarModelOverViewFragment;
import com.spade.mazda.ui.cars.view.fragments.CarModelSpecsFragment;
import com.spade.mazda.ui.cars.view.interfaces.CarDetailsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarDetailsPresenterImpl implements CarDetailsPresenter, CarModelOverViewFragment.OnCalculatorClicked {

    private Context context;
    private CarDetailsView carDetailsView;
    private CarModelOverViewFragment carModelOverViewFragment;
    private CarModelSpecsFragment carModelSpecsFragment;
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
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carModel -> carDetailsView.showCarYearsAndTrims(carModel.getCarYears()));
    }

    @Override
    public void setUpViewPagerFragments() {
        carModelOverViewFragment = new CarModelOverViewFragment();
        carModelOverViewFragment.setOnCalculatorClicked(this);
        carModelSpecsFragment = new CarModelSpecsFragment();

        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmentTitles = new ArrayList<>();

        fragments.add(carModelOverViewFragment);
        fragments.add(carModelSpecsFragment);
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
                    carModelSpecsFragment.updateSpecs(carDetailsData.getCategories());
                    carModelOverViewFragment.updateUI(carDetailsData);
                    carDetailsView.updateUI(carDetailsData.getImage(), carDetailsData.getName());
                }, throwable -> {
                });
    }

    @Override
    public void onCalculatorClicked() {
        carDetailsView.navigateToCalculator();
    }

//    @Override
//    public void onBookNowClicked() {
//        carDetailsView.navigateToBook();
//    }
}
