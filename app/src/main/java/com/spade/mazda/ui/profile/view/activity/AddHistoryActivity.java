package com.spade.mazda.ui.profile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.profile.view.fragment.AddHistoryFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class AddHistoryActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.add_history);
    }

    @Override
    protected void addFragment() {
        AddHistoryFragment addHistoryFragment = new AddHistoryFragment();
        showFragment(addHistoryFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AddHistoryActivity.class);
    }
}
