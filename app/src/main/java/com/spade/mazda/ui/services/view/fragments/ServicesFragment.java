package com.spade.mazda.ui.services.view.fragments;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.fabrika.view.TradeInActivity;
import com.spade.mazda.ui.general.view.LoginDialogFragment;
import com.spade.mazda.ui.services.view.activities.AfterSalesActivity;
import com.spade.mazda.ui.services.view.activities.FinanceActivity;
import com.spade.mazda.ui.services.view.activities.FixologyActivity;
import com.spade.mazda.ui.services.view.activities.Places360Activity;
import com.spade.mazda.ui.services.view.activities.SparePartsActivity;
import com.spade.mazda.ui.services.view.adapters.ServicesAdapter;
import com.spade.mazda.utils.PrefUtils;

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
                startActivity(AfterSalesActivity.getLaunchIntent(getContext()));
                break;
            case 1:
                startActivity(SparePartsActivity.getLaunchIntent(getContext()));
                break;
            case 2:
                checkToNavigateToTradeIn();
                break;
            case 3:
                startActivity(FixologyActivity.getLaunchIntent(getContext()));
                break;
            case 4:
                startActivity(Places360Activity.getLaunchIntent(getContext()));
                break;
            case 5:
                startActivity(FinanceActivity.getLaunchIntent(getContext()));
                break;
        }
    }

    private void checkToNavigateToTradeIn() {
        if (PrefUtils.isLoggedIn(getContext())) {
            startActivity(TradeInActivity.getLaunchIntent(getContext()));
        } else {
            showLoginDialog();
        }
    }

    private void showLoginDialog() {
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(getChildFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }
}
