package com.spade.mazda.ui.cars.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class CarDetailsActivity extends ToolBarBaseActivity {
    public static final String EXTRA_CAR_NAME = "EXTRA_CAR_NAME";

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        init();
//    }


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
        setTitle(getIntent().getStringExtra(EXTRA_CAR_NAME));
    }

    @Override
    protected void addFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(CarDetailsFragment.EXTRA_CAR_ID, getIntent().getStringExtra(CarDetailsFragment.EXTRA_CAR_ID));
//        bundle.putParcelableArrayList(EXTRA_CAR_YEARS, getIntent().getParcelableArrayListExtra(EXTRA_CAR_YEARS));
        CarDetailsFragment carDetailsFragment = new CarDetailsFragment();
        carDetailsFragment.setArguments(bundle);
        showFragment(carDetailsFragment);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carDetailsFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, CarDetailsActivity.class);
    }
}
