package com.spade.mazda.ui.services.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.general.view.LocationsSpinnerAdapter;
import com.spade.mazda.ui.services.model.Location;
import com.spade.mazda.ui.services.model.ServicesLocation;
import com.spade.mazda.ui.services.presenter.ServicesLocationsPresenter;
import com.spade.mazda.ui.services.presenter.ServicesLocationsPresenterImpl;
import com.spade.mazda.ui.services.view.adapters.ServicesLocationsAdapter;
import com.spade.mazda.ui.services.view.interfaces.ServicesLocationsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class MaintenanceLocationsFragment extends BaseFragment implements ServicesLocationsView {
    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    private View view;
    private ProgressBar progressBar;
    private ServicesLocationsPresenter servicesLocationsPresenter;
    private List<ServicesLocation> servicesLocations = new ArrayList<>();
    private List<Location> locationList = new ArrayList<>();
    private LocationsSpinnerAdapter locationsSpinnerAdapter;
    private ServicesLocationsAdapter servicesLocationsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maintenance, container, false);
        initViews();
        return view;
    }

    @Override
    protected void initPresenter() {
        servicesLocationsPresenter = new ServicesLocationsPresenterImpl(getContext());
        servicesLocationsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomRecyclerView servicesLocationsRecycler = view.findViewById(R.id.items_recycler_view);
        AppCompatSpinner locationSpinner = view.findViewById(R.id.locations_spinner);
        LinearLayout filterLayout = view.findViewById(R.id.select_location_layout);
        progressBar = view.findViewById(R.id.progress_bar);
        filterLayout.setVisibility(View.VISIBLE);

        servicesLocationsAdapter = new ServicesLocationsAdapter(getContext(), servicesLocations);
        locationsSpinnerAdapter = new LocationsSpinnerAdapter(locationList, getContext());

        servicesLocationsRecycler.setAdapter(servicesLocationsAdapter);
        locationSpinner.setAdapter(locationsSpinnerAdapter);

//        String type = getArguments().getString(EXTRA_TYPE);

        servicesLocationsPresenter.getServicesLocation(ApiHelper.AFTER_SALES_LOCATIONS_PARAM);
        servicesLocationsPresenter.getLocations();

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Location location = locationList.get(i);
                if (location.getId() == -1) {
                    servicesLocationsPresenter.getAllServiceLocation();
                } else {
                    servicesLocationsPresenter.getServiceLocationsByLocationId(location.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    public void showServicesLocations(List<ServicesLocation> servicesLocations) {
        this.servicesLocations.clear();
        this.servicesLocations.addAll(servicesLocations);
        this.servicesLocationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLocations(List<Location> locations) {
        Location location = new Location();
        location.setId(-1);
        location.setName(getString(R.string.choose_location));
        this.locationList.clear();
        this.locationList.add(location);
        this.locationList.addAll(locations);
        locationsSpinnerAdapter.notifyDataSetChanged();
    }
}
