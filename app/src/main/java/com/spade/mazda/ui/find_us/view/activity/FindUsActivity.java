package com.spade.mazda.ui.find_us.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class FindUsActivity extends ToolBarBaseActivity {


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
        setTitle(R.string.find_us);
    }

    @Override
    protected void addFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(FindUsFragment.EXTRA_POSITION,
                getIntent().getIntExtra(FindUsFragment.EXTRA_POSITION, FindUsFragment.SHOWROOMS_TYPE));
        FindUsFragment findUsFragment = new FindUsFragment();
        findUsFragment.setArguments(bundle);
        showFragment(findUsFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, FindUsActivity.class);
    }
}
