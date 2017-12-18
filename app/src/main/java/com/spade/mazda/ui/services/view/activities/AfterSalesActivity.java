package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.services.view.fragments.AfterSalesFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class AfterSalesActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.after_sales_service);
    }

    @Override
    protected void addFragment() {
        AfterSalesFragment afterSalesFragment = new AfterSalesFragment();
        showFragment(afterSalesFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AfterSalesActivity.class);
    }
}
