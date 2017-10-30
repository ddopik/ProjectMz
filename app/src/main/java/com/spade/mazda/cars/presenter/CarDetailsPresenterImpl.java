package com.spade.mazda.cars.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.spade.mazda.R;
import com.spade.mazda.cars.view.CarDetailsView;
import com.spade.mazda.cars.view.FragmentCarModelOverView;
import com.spade.mazda.cars.view.FragmentCarModelSpecs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarDetailsPresenterImpl implements CarDetailsPresenter {

    private Context context;
    private FragmentCarModelOverView fragmentCarModelOverView;
    private FragmentCarModelSpecs fragmentCarModelSpecs;
    private CarDetailsView carDetailsView;

    public CarDetailsPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(CarDetailsView view) {
        this.carDetailsView = view;
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
}
