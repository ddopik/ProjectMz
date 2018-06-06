package com.spade.mazda.ui.find_us.view.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.find_us.presenter.BranchesPresenter;
import com.spade.mazda.ui.find_us.presenter.BranchesPresenterImpl;
import com.spade.mazda.ui.find_us.view.adapters.BranchesAdapter;
import com.spade.mazda.ui.find_us.view.adapters.CitySpinnerAdapter;
import com.spade.mazda.ui.find_us.view.interfaces.BranchesView;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class BranchesFragment extends BaseFragment implements BranchesView, OnMapReadyCallback {
    private int tabType;
    private BranchesPresenter branchesPresenter;
    private View branchesView;
    private GoogleMap googleMap;
    private ProgressBar progressBar;
    private BranchesAdapter branchesAdapter;
    private CitySpinnerAdapter citySpinnerAdapter;
    private List<Branch> allBranchList, filteredBranchesList;
    private List<City> cityList = new ArrayList<>();
    private AppCompatSpinner citiesSpinner;
    private DataSource dataSource = DataSource.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        branchesView = inflater.inflate(R.layout.fragment_branch, container, false);
        initViews();
        return branchesView;
    }

    @Override
    protected void initPresenter() {
        branchesPresenter = new BranchesPresenterImpl(getContext());
        branchesPresenter.setView(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {
        ScrollView mainScroll = branchesView.findViewById(R.id.main_scroll);
        RecyclerView recyclerView = branchesView.findViewById(R.id.items_recycler_view);
        ImageView transparentImage = branchesView.findViewById(R.id.transparent_image);
        progressBar = branchesView.findViewById(R.id.progress_bar);
        citiesSpinner = branchesView.findViewById(R.id.items_spinner);
        recyclerView.setNestedScrollingEnabled(false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        allBranchList = new ArrayList<>();
        filteredBranchesList = new ArrayList<>();

        branchesAdapter = new BranchesAdapter(getContext(), filteredBranchesList);
        branchesAdapter.setGetDirectionAction((lat, lng) -> {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lng));
//                    Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
            startActivity(intent);
        });
        citySpinnerAdapter = new CitySpinnerAdapter(cityList, getContext());

        citiesSpinner.setAdapter(citySpinnerAdapter);
        recyclerView.setAdapter(branchesAdapter);


        branchesPresenter.getBranches(getTabType(), PrefUtils.getAppLang(getContext()));

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    showFilteredBranches(allBranchList);
                } else {
                    City city = cityList.get(i);
                    getBranchesByCity(city.getCityId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        transparentImage.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mainScroll.requestDisallowInterceptTouchEvent(true);
                    return false;

                case MotionEvent.ACTION_UP:
                    mainScroll.requestDisallowInterceptTouchEvent(false);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    mainScroll.requestDisallowInterceptTouchEvent(true);
                    return false;
                default:
                    return true;
            }
        });
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
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
    public void showBranches(List<Branch> branchList) {
        this.allBranchList.clear();
        this.allBranchList.addAll(branchList);
        this.filteredBranchesList.addAll(branchList);
        this.branchesAdapter.notifyDataSetChanged();
        branchesPresenter.getCities(branchList);
        branchesPresenter.getMapPins(branchList);
    }

    @Override
    public void showFilteredBranches(List<Branch> branchList) {
        this.filteredBranchesList.clear();
        this.filteredBranchesList.addAll(branchList);
        this.branchesAdapter.notifyDataSetChanged();
        branchesPresenter.getMapPins(branchList);
    }

    @Override
    public void showPins(List<LatLng> latLngList) {
        googleMap.clear();
        for (LatLng latLng : latLngList) {
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(bitmapDescriptorFromVector(getActivity(), R.drawable.pin)));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(13)
                    .build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.pin);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getContext(), R.raw.map_style));
    }

    public void showCities(List<City> cityList) {
        if (cityList != null && !cityList.isEmpty()) {
            this.cityList.clear();
            this.cityList.addAll(cityList);
            this.citySpinnerAdapter.notifyDataSetChanged();
            this.citiesSpinner.setVisibility(View.VISIBLE);
        } else {
            this.citiesSpinner.setVisibility(View.GONE);
        }
    }

    @SuppressLint("CheckResult")
    private void getBranchesByCity(int cityId) {
        dataSource.getBranchByCityId(allBranchList, cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showFilteredBranches);
    }
}
