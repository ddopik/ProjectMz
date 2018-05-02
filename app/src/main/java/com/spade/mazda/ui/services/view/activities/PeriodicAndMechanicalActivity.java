package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.services.view.fragments.PeriodicAndMechanicalFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class PeriodicAndMechanicalActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.periodic);

    }

    @Override
    protected void addFragment() {
        PeriodicAndMechanicalFragment periodicAndMechanicalFragment = new PeriodicAndMechanicalFragment();
        showFragment(periodicAndMechanicalFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, PeriodicAndMechanicalActivity.class);
    }

}
