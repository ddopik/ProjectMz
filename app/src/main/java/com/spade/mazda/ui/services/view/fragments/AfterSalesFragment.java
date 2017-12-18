package com.spade.mazda.ui.services.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.general.presenter.PageDescriptionPresenter;
import com.spade.mazda.ui.general.presenter.PageDescriptionPresenterImpl;
import com.spade.mazda.ui.general.view.PageDescriptionView;
import com.spade.mazda.ui.services.view.activities.MaintenanceLocationsActivity;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public class AfterSalesFragment extends BaseFragment implements PageDescriptionView {

    private View afterSalesView;
    private PageDescriptionPresenter pageDescriptionPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        afterSalesView = inflater.inflate(R.layout.fragment_after_sales_services, container, false);
        initViews();
        return afterSalesView;
    }

    @Override
    protected void initPresenter() {
        pageDescriptionPresenter = new PageDescriptionPresenterImpl();
        pageDescriptionPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomTextView descriptionTextView = afterSalesView.findViewById(R.id.after_sales_description);
        CustomTextView maintenanceTextView = afterSalesView.findViewById(R.id.maintenance_text_view);
        CustomTextView periodicTextView = afterSalesView.findViewById(R.id.periodic_text_view);
        maintenanceTextView.setOnClickListener(view -> openMaintenanceActivity());

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showDescription(String pageDescription) {

    }

    private void openMaintenanceActivity() {
        Intent afterSalesIntent = MaintenanceLocationsActivity.getLaunchIntent(getContext());
        afterSalesIntent.putExtra(MaintenanceLocationsFragment.EXTRA_TYPE, ApiHelper.AFTER_SALES_LOCATIONS_PARAM);
        startActivity(afterSalesIntent);
    }
}
