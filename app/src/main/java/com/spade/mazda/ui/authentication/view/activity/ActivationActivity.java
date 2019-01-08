package com.spade.mazda.ui.authentication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;
import com.spade.mazda.ui.authentication.view.fragment.ActivationFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class ActivationActivity extends BaseActivity {
    public static final String EXTRA_EMAIL = "EXTRA_EMAIL";

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
        setTitle(R.string.activation);
        addFragment();
    }

    @Override
    protected void addFragment() {
        ActivationFragment activationFragment = new ActivationFragment();
        if (getIntent() != null && getIntent().getStringExtra(EXTRA_EMAIL) != null) {
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_EMAIL, getIntent().getStringExtra(EXTRA_EMAIL));
            activationFragment.setArguments(bundle);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, activationFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, ActivationActivity.class);
    }
}
