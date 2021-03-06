package com.spade.mazda.ui.home.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spade.mazda.R;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.home.view.HomeFragment;
import com.spade.mazda.ui.home.view.HomeView;
import com.spade.mazda.utils.PermationController;
import com.spade.mazda.utils.PrefUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by Ayman Abouzeid on 12/6/17.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;
    private Context context;
    ////
    public static final String RESULTS = "results";
    public static final String STATUS = "status";
    public static final String OK = "OK";
    public static final String ZERO_RESULTS = "ZERO_RESULTS";
    public static final int LOCATION_PERMEATION_REQUEST_CODE = 123;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final int PROXIMITY_RADIUS = 5000;
    public static final String GEOMETRY = "geometry";
    public static final String LOCATION = "location";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String ICON = "icon";
    public static final String SUPERMARKET_ID = "id";
    public static final String NAME = "name";
    public static final String PLACE_ID = "place_id";
    public static final String REFERENCE = "reference";
    public static final String VICINITY = "vicinity";
    public static final String PLACE_NAME = "place_name";
    private LocationManager locationManager;





    public HomePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(HomeView view) {
        this.homeView = view;
    }





    @SuppressLint("CheckResult")
    @Override
    public void getOffers() {
        homeView.showLoading();
        ApiHelper.getOffers(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(offersResponse -> {
                    homeView.hideLoading();
                    homeView.showOffers(offersResponse.getData());
                }, throwable -> {
                    Log.e(HomePresenterImpl.class.getSimpleName(), "Error ---->" + throwable.getMessage());
                });
    }


    @Override
    @SuppressLint("CheckResult")
    public void loadNearByPlaces(double latitude, double longitude) {
        ApiHelper.getNearByPlaces(context.getString(R.string.GOOGLE_MAPS_API_KEY), PROXIMITY_RADIUS, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> parseLocationResult(response), throwable -> {
                    Log.e(HomeFragment.class.getSimpleName(), "onErrorResponse: Error= " + throwable);
                    Log.e(HomeFragment.class.getSimpleName(), "onErrorResponse: Error= " + throwable.getMessage());
                });


    }



    private void parseLocationResult(JSONObject result) {

        String id, place_id, placeName = null, reference, icon, vicinity = null;
        double latitude, longitude;

        try {
            JSONArray jsonArray = result.getJSONArray("results");

            if (result.getString(STATUS).equalsIgnoreCase(OK)) {

                homeView.getMapView().clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject place = jsonArray.getJSONObject(i);

                    id = place.getString(SUPERMARKET_ID);
                    place_id = place.getString(PLACE_ID);
                    if (!place.isNull(NAME)) {
                        placeName = place.getString(NAME);
                    }
                    if (!place.isNull(VICINITY)) {
                        vicinity = place.getString(VICINITY);
                    }
                    latitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LATITUDE);
                    longitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LONGITUDE);
                    reference = place.getString(REFERENCE);
                    icon = place.getString(ICON);

                    MarkerOptions markerOptions = new MarkerOptions().icon(bitmapDescriptorFromVector(context, R.drawable.pin));
                    LatLng latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(placeName + " : " + vicinity);

                    homeView.getMapView().addMarker(markerOptions);

                }


            } else if (result.getString(STATUS).equalsIgnoreCase(ZERO_RESULTS)) {
//                Toast.makeText(context, "No Item found in specified  radius!!!",
//                        Toast.LENGTH_LONG).show();
            }

        } catch (org.json.JSONException e) {

            e.printStackTrace();
            Log.e(HomeFragment.class.getSimpleName(), "parseLocationResult: Error=" + e.getMessage());
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


}
