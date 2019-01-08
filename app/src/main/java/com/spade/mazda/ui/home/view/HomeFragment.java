package com.spade.mazda.ui.home.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.home.model.Offer;
import com.spade.mazda.ui.home.presenter.HomePresenter;
import com.spade.mazda.ui.home.presenter.HomePresenterImpl;
import com.spade.mazda.ui.home.view.adapters.OffersPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public class HomeFragment extends BaseFragment implements HomeView, OnMapReadyCallback, LocationListener {

    private HomePresenter homePresenter;
    private View homeView;
    private RelativeLayout callImageView;

    // The minimum distance to change Updates in meters
    public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 5; // 10 meters
    private ProgressBar progressBar;
    private OffersPagerAdapter offersPagerAdapter;
    private List<Offer> offers = new ArrayList<>();
    private OnNearestServiceClicked onNearestServiceClicked;
    // The minimum time between updates in milliseconds
    public static final long MIN_TIME_BW_UPDATES = 1000 * 30; // 1 minute
    LocationManager locationManager;
    ////////////////////////
    private GoogleMap googleMap;

////////////////////////

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        return homeView;
    }

    @Override
    public GoogleMap getMapView() {
        return googleMap;
    }

    @Override
    protected void initPresenter() {
        homePresenter = new HomePresenterImpl(getActivity());
        homePresenter.setView(this);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        homePresenter.checkPermutation(locationManager);
    }


    @Override
    protected void initViews() {
        RelativeLayout nearestServiceLayout = homeView.findViewById(R.id.nearest_service_layout);
        ViewPager offersViewPager = homeView.findViewById(R.id.offers_pager);
        TabLayout tabLayout = homeView.findViewById(R.id.tab_layout);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        progressBar = homeView.findViewById(R.id.progress_bar);
        callImageView=homeView.findViewById(R.id.call_btn_container);
        offersPagerAdapter = new OffersPagerAdapter(getContext(), offers);
        offersViewPager.setAdapter(offersPagerAdapter);
        tabLayout.setupWithViewPager(offersViewPager, true);
        homePresenter.getOffers();
        nearestServiceLayout.setOnClickListener(view -> onNearestServiceClicked.onNearestServiceClicked());
        callImageView.setOnClickListener(view -> callMazdaCenter());


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


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
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getContext(), R.raw.map_style));
        homePresenter.viewNearByPlaces(locationManager);
    }


    public void setOnNearestServiceClicked(OnNearestServiceClicked onNearestServiceClicked) {
        this.onNearestServiceClicked = onNearestServiceClicked;
    }

    public interface OnNearestServiceClicked {
        void onNearestServiceClicked();
    }


    /////////Find near by Location Block[]

    /*
     * Presenter Update  are reflects  here
     * */
    @SuppressLint("MissingPermission")
    @Override
    public void setMyLocation() {
        Criteria criteria = new Criteria();
        //todo investigate this line
        String bestProvider = locationManager.getBestProvider(criteria, true);
        /////
        googleMap.setMyLocationEnabled(true);
        locationManager.requestLocationUpdates(bestProvider, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
    }


    @Override
    public void onLocationChanged(Location location) {


        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        showNearByLocations(latitude, longitude);
    }

    @Override
    public void showNearByLocations(double latitude, double longitude) {

        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        homePresenter.loadNearByPlaces(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }


    private void callMazdaCenter() {

        final Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getResources().getString(R.string.mazda_number)));
        if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_dial_support), Toast.LENGTH_LONG).show();
        }


    }
}

/////////////////


