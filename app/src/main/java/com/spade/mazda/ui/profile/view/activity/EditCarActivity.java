package com.spade.mazda.ui.profile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.profile.view.fragment.EditCarFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class EditCarActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.edit_profile);
    }

    @Override
    protected void addFragment() {
        EditCarFragment editCarFragment = new EditCarFragment();
        showFragment(editCarFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, EditCarActivity.class);
    }
}
