package com.spade.mazda.cars.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.cars.presenter.interfaces.CarDetailsPresenter;
import com.spade.mazda.cars.presenter.CarDetailsPresenterImpl;
import com.spade.mazda.cars.view.interfaces.CarDetailsView;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarDetailsFragment extends BaseFragment implements CarDetailsView {

    private CarDetailsPresenter carDetailsPresenter;
    private View carDetailsView;
    private PagingAdapter pagingAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carDetailsView = inflater.inflate(R.layout.fragment_pager, container, false);
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
        viewPager = carDetailsView.findViewById(R.id.fragments_viewpager);
        tabLayout = carDetailsView.findViewById(R.id.tabs);
        pagingAdapter = new PagingAdapter(getChildFragmentManager());
        carDetailsPresenter.setUpViewPagerFragments();
//        pagingAdapter.addFragment();
//        viewPager.setAdapter(pagingAdapter);
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
    public void showCarDetails() {

    }

    @Override
    public void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles) {
        pagingAdapter.addFragment(fragmentList, fragmentTitles);
        viewPager.setAdapter(pagingAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
