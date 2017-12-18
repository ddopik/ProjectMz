package com.spade.mazda.ui.cars.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment;
import com.spade.mazda.ui.cars.view.fragments.DriveFinanceFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class DriveFinanceActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.drive_finance);
    }

    @Override
    protected void addFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(CarDetailsFragment.EXTRA_CAR_ID, getIntent().getIntExtra(CarDetailsFragment.EXTRA_CAR_ID, 0));
        DriveFinanceFragment driveFinanceFragment = new DriveFinanceFragment();
        driveFinanceFragment.setArguments(bundle);
        showFragment(driveFinanceFragment);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, financeFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, DriveFinanceActivity.class);
    }
}
