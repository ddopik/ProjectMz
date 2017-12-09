package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;
import com.spade.mazda.ui.services.view.fragments.DriveFinanceFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class DriveFinanceActivity extends BaseActivity {

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
        setTitle(R.string.drive_finance);
    }

    @Override
    protected void addFragment() {
        DriveFinanceFragment driveFinanceFragment = new DriveFinanceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, driveFinanceFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, DriveFinanceActivity.class);
    }
}
