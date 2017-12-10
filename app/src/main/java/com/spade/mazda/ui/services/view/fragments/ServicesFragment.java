package com.spade.mazda.ui.services.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.activities.Places360Activity;
import com.spade.mazda.ui.services.view.activities.ServicesLocationsActivity;
import com.spade.mazda.ui.services.view.activities.SparePartsActivity;
import com.spade.mazda.ui.services.view.adapters.ServicesAdapter;

/**
 * Created by Ayman Abouzeid on 10/31/17.
 */

public class ServicesFragment extends BaseFragment implements ServicesAdapter.OnServiceClicked {
    private View servicesView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        servicesView = inflater.inflate(R.layout.fragment_listing, container, false);
        initViews();
        return servicesView;
    }

    @Override
    protected void initPresenter() {

    }

    @SuppressLint("Recycle")
    @Override
    protected void initViews() {
        CustomRecyclerView servicesRecyclerView = servicesView.findViewById(R.id.items_recycler_view);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.after_sales_services_images);
        ServicesAdapter servicesAdapter = new ServicesAdapter(getContext(), typedArray);
        servicesAdapter.setOnServiceClicked(this);
        servicesRecyclerView.setAdapter(servicesAdapter);
    }

    @Override
    public void onServiceClicked(int position) {
        switch (position) {
            case 0:
                Intent afterSalesIntent = ServicesLocationsActivity.getLaunchIntent(getContext());
                afterSalesIntent.putExtra(ServicesLocationsFragment.EXTRA_TYPE, ApiHelper.AFTER_SALES_LOCATIONS_PARAM);
                startActivity(afterSalesIntent);
                break;
            case 1:
                startActivity(SparePartsActivity.getLaunchIntent(getContext()));
                break;
            case 2:
                break;
            case 3:
                Intent fixologyIntent = ServicesLocationsActivity.getLaunchIntent(getContext());
                fixologyIntent.putExtra(ServicesLocationsFragment.EXTRA_TYPE, ApiHelper.FIXOLOGY_LOCATIONS_PARAM);
                startActivity(fixologyIntent);
                break;
            case 4:
                startActivity(Places360Activity.getLaunchIntent(getContext()));
                break;
            case 5:
                break;
        }
    }
}