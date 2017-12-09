package com.spade.mazda.ui.authentication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.ui.authentication.view.fragment.RegistrationFirstStepFragment;
import com.spade.mazda.ui.authentication.view.fragment.RegistrationSecondStepFragment;
import com.spade.mazda.base.BaseActivity;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class RegistrationActivity extends BaseActivity implements RegistrationFirstStepFragment.OnNextClicked {

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
        setTitle(R.string.register);
        addFragment();
    }

    @Override
    protected void addFragment() {
        RegistrationFirstStepFragment registrationFirstStepFragment = new RegistrationFirstStepFragment();
        registrationFirstStepFragment.setOnNextClicked(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registrationFirstStepFragment).commit();
    }

    @Override
    public void onNextClicked() {
        RegistrationSecondStepFragment registrationSecondStepFragment = new RegistrationSecondStepFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registrationSecondStepFragment)
                .addToBackStack(RegistrationSecondStepFragment.class.getSimpleName()).commit();
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}
