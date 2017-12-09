package com.spade.mazda.ui.find_us.view.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    private List<Branch> branchList;
    private List<City> cityList;
    private AppCompatSpinner citiesSpinner;

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

//    double lat = Double.parseDouble(area.getLat());
//    double lng = Double.parseDouble(area.getLng());
//    LatLng storeLocation = new LatLng(lat, lng);
////        mMap.clear();
//        mMap.addMarker(new MarkerOptions().position(storeLocation).title(area.getArea()));
//    CameraPosition cameraPosition = new CameraPosition.Builder()
//            .target(storeLocation)
//            .zoom(13)
//            .build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    @Override
    protected void initViews() {
        RecyclerView recyclerView = branchesView.findViewById(R.id.items_recycler_view);
        citiesSpinner = branchesView.findViewById(R.id.items_spinner);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        branchList = new ArrayList<>();
        cityList = new ArrayList<>();

        progressBar = branchesView.findViewById(R.id.progress_bar);

        branchesAdapter = new BranchesAdapter(getContext(), branchList);
        citySpinnerAdapter = new CitySpinnerAdapter(cityList, getContext());

        citiesSpinner.setAdapter(citySpinnerAdapter);
        recyclerView.setAdapter(branchesAdapter);

        showCities();
        branchesPresenter.getBranches(getTabType(), PrefUtils.getAppLang(getContext()));
    }

    //transparentImage.setOnTouchListener((v, event) -> {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mainScroll.requestDisallowInterceptTouchEvent(true);
//                return false;
//
//            case MotionEvent.ACTION_UP:
//                mainScroll.requestDisallowInterceptTouchEvent(false);
//                return true;
//
//            case MotionEvent.ACTION_MOVE:
//                mainScroll.requestDisallowInterceptTouchEvent(true);
//                return false;
//            default:
//                return true;
//        }
//    });
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
        this.branchList.addAll(branchList);
        this.branchesAdapter.notifyDataSetChanged();
        branchesPresenter.getMapPins(branchList).subscribe(latLng -> googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.fixology))));
    }

    @Override
    public void showPins(List<LatLng> latLngList) {

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
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.map_style));

            if (!success) {
//                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
//            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    public void showCities() {
        DataSource dataSource = DataSource.getInstance();
        List<City> cityList = dataSource.getCityList();
        if (cityList != null && !cityList.isEmpty()) {
            this.citiesSpinner.setVisibility(View.VISIBLE);
            this.cityList.addAll(cityList);
            this.citySpinnerAdapter.notifyDataSetChanged();
        } else {
            this.citiesSpinner.setVisibility(View.GONE);
        }
    }
}
