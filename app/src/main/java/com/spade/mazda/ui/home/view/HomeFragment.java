package com.spade.mazda.ui.home.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import com.spade.mazda.utils.MapUtls;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public class HomeFragment extends BaseFragment implements HomeView,OnMapReadyCallback, MapUtls.OnLocationUpdate,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

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
    ////////////////////////
    private final int RC_LOCATION = 1239;
    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager locationManager;
    private MapUtls mapUtls;


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
    }


    @Override
    protected void initViews() {
        RelativeLayout nearestServiceLayout = homeView.findViewById(R.id.nearest_service_layout);
        ViewPager offersViewPager = homeView.findViewById(R.id.offers_pager);
        TabLayout tabLayout = homeView.findViewById(R.id.tab_layout);
        mapUtls = new MapUtls(this);
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));

        requestLocationPermission();


    }


    public void setOnNearestServiceClicked(OnNearestServiceClicked onNearestServiceClicked) {
        this.onNearestServiceClicked = onNearestServiceClicked;
    }

    public interface OnNearestServiceClicked {
        void onNearestServiceClicked();
    }



    @SuppressLint("MissingPermission")
    @Override
    public void showNearByLocations(double latitude, double longitude) {

        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.setMyLocationEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(9));
        homePresenter.loadNearByPlaces(latitude, longitude);
    }




    private void callMazdaCenter() {

        final Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getResources().getString(R.string.mazda_number)));
        if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_dial_support), Toast.LENGTH_LONG).show();
        }


    }






    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationUpdate(Location location) {

//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        showNearByLocations(location.getLatitude(), location.getLongitude());
        mapUtls.removeLocationRequest();
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(RC_LOCATION)
    private void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {


            mapUtls.startLocationUpdates(getActivity(), MapUtls.MapConst.UPDATE_INTERVAL_INSTANT);

//            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//            locationManager.requestLocationUpdates("gps", 0, 0, this);

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.location), RC_LOCATION, perms);
            //            Toast.makeText(context, context.getResources().getString(R.string.enable_gps), Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            context.startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }



}



