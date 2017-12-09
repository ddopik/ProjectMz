package com.spade.mazda.ui.home.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.home.model.Offer;
import com.spade.mazda.ui.home.presenter.HomePresenter;
import com.spade.mazda.ui.home.presenter.HomePresenterImpl;
import com.spade.mazda.ui.home.view.adapters.OffersPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public class HomeFragment extends BaseFragment implements HomeView, OnMapReadyCallback {

    private HomePresenter homePresenter;
    private View homeView;
    private GoogleMap googleMap;

    private ProgressBar progressBar;
    private OffersPagerAdapter offersPagerAdapter;
    private List<Offer> offers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        return homeView;
    }

    @Override
    protected void initPresenter() {
        homePresenter = new HomePresenterImpl(getContext());
        homePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        ViewPager offersViewPager = homeView.findViewById(R.id.offers_pager);
        TabLayout tabLayout = homeView.findViewById(R.id.tab_layout);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        progressBar = homeView.findViewById(R.id.progress_bar);
        offersPagerAdapter = new OffersPagerAdapter(getContext(), offers);
        offersViewPager.setAdapter(offersPagerAdapter);
        tabLayout.setupWithViewPager(offersViewPager, true);
        homePresenter.getOffers();
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
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showOffers(List<Offer> offerList) {
        this.offers.clear();
        this.offers.addAll(offerList);
        offersPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        try {
            googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.map_style));
        } catch (Resources.NotFoundException e) {
        }
    }
}
