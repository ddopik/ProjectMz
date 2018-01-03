package com.spade.mazda.ui.find_us.view.fragments;

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
import com.spade.mazda.ui.cars.view.adapter.PagingAdapter;
import com.spade.mazda.ui.find_us.presenter.FindUsPresenter;
import com.spade.mazda.ui.find_us.presenter.FindUsPresenterImpl;
import com.spade.mazda.ui.find_us.view.interfaces.FindUsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/5/17.
 */

public class FindUsFragment extends BaseFragment implements FindUsView {
    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    public static final int SHOWROOMS_TYPE = 1;
    public static final int DEALERS_TYPE = 2;
    public static final int SPARE_PARTS_TYPE = 3;
    public static final int SERVICE_CENTER_TYPE = 4;

    private ViewPager viewPager;
    private View findUsView;
    private FindUsPresenter findUsPresenter;
    //    private int tabType;
    private PagingAdapter pagingAdapter;
    private TabLayout tabLayout;
    private List<Fragment> branchesFragments = new ArrayList<>();
    private int currentItemPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        findUsView = inflater.inflate(R.layout.fragment_pager, container, false);
        initViews();
        return findUsView;
    }

    @Override
    protected void initPresenter() {
        findUsPresenter = new FindUsPresenterImpl(getContext());
        findUsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        viewPager = findUsView.findViewById(R.id.fragments_viewpager);
        tabLayout = findUsView.findViewById(R.id.tabs);
        pagingAdapter = new PagingAdapter(getChildFragmentManager());
        findUsPresenter.setUpViewPager();
        if (getArguments().containsKey(EXTRA_POSITION)) {
            currentItemPosition = getArguments().getInt(EXTRA_POSITION);
            currentItemPosition -= 1;
        }
        viewPager.setCurrentItem(currentItemPosition);
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
    public void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles) {
        pagingAdapter.addFragment(fragmentList, fragmentTitles);
        viewPager.setAdapter(pagingAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
