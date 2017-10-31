package com.spade.mazda.services;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.settings.view.CustomRecyclerView;

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

    @Override
    protected void initViews() {
        CustomRecyclerView servicesRecyclerView = servicesView.findViewById(R.id.items_recycler_view);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.after_sales_services_images);
        ServicesAdapter servicesAdapter = new ServicesAdapter(getContext(), typedArray);
        servicesAdapter.setOnServiceClicked(this);
//        typedArray.recycle();

        servicesRecyclerView.setAdapter(servicesAdapter);
    }

    @Override
    public void onServiceClicked(int position) {

    }
}
