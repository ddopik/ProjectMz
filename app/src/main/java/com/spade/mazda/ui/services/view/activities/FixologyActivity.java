package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.services.view.fragments.FixologyFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class FixologyActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.fixology);
    }

    @Override
    protected void addFragment() {
        FixologyFragment fixologyFragment = new FixologyFragment();
        showFragment(fixologyFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, FixologyActivity.class);
    }
}
