package com.spade.mazda.ui.find_us.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.find_us.view.fragments.BranchesFragment;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.find_us.view.interfaces.FindUsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class FindUsPresenterImpl implements FindUsPresenter {

    private Context context;
    private FindUsView findUsView;
    private BranchesFragment showRoomFragment, dealerFragment, sparePartsOutletFragment;

    public FindUsPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(FindUsView view) {
        findUsView = view;
    }

    @Override
    public void setUpViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmentTitles = new ArrayList<>();

        showRoomFragment = getBranchFragmentInstance(FindUsFragment.SHOWROOMS_TYPE);
        dealerFragment = getBranchFragmentInstance(FindUsFragment.DEALERS_TYPE);
        sparePartsOutletFragment = getBranchFragmentInstance(FindUsFragment.SPARE_PARTS_TYPE);

        fragments.add(showRoomFragment);
        fragments.add(dealerFragment);
        fragments.add(sparePartsOutletFragment);

        fragmentTitles.add(context.getString(R.string.showrooms));
        fragmentTitles.add(context.getString(R.string.dealers));
        fragmentTitles.add(context.getString(R.string.spare_parts_outlets));

        findUsView.addFragment(fragments, fragmentTitles);
    }

    @Override
    public void getCities(String appLang) {
        ApiHelper.getCities(appLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(citiesResponse -> {
                    if (citiesResponse != null && citiesResponse.getCityList() != null) {
                        DataSource dataSource = DataSource.getInstance();
                        dataSource.setCityList(citiesResponse.getCityList());
//                        showRoomFragment.showCities(citiesResponse.getCityList());
//                        dealerFragment.showCities(citiesResponse.getCityList());
//                        sparePartsOutletFragment.showCities(citiesResponse.getCityList());
                    }
                }, throwable -> {
//                    if (throwable != null)
//                        welcomeView.showMessage(throwable.getMessage());
                });
    }

    private BranchesFragment getBranchFragmentInstance(int type) {
        BranchesFragment branchesFragment = new BranchesFragment();
        branchesFragment.setTabType(type);
        return branchesFragment;
    }
}
