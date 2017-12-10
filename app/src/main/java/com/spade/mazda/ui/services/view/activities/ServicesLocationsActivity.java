package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.services.view.fragments.ServicesLocationsFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class ServicesLocationsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void init() {
        addFragment();
    }

    @Override
    protected void addFragment() {
        String type = getIntent().getStringExtra(ServicesLocationsFragment.EXTRA_TYPE);
        if (type.equals(ApiHelper.AFTER_SALES_LOCATIONS_PARAM)) {
            setTitle(R.string.after_sales_service);
        } else {
            setTitle(R.string.fixology);
        }
        Bundle bundle = new Bundle();
        bundle.putString(ServicesLocationsFragment.EXTRA_TYPE, type);
        ServicesLocationsFragment servicesLocationsFragment = new ServicesLocationsFragment();
        servicesLocationsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, servicesLocationsFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, ServicesLocationsActivity.class);
    }
}
