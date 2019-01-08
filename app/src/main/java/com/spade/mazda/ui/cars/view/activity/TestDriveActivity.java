package com.spade.mazda.ui.cars.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.cars.view.fragments.TestDriveFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class TestDriveActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.test_drive);
    }

    @Override
    protected void addFragment() {
        TestDriveFragment testDriveFragment = new TestDriveFragment();
        showFragment(testDriveFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, TestDriveActivity.class);
    }
}
