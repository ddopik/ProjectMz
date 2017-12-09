package com.spade.mazda.ui.welcome.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;

/**
 * Created by Ayman Abouzeid on 11/22/17.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
    }

    @Override
    protected void init() {
        addFragment();
    }

    @Override
    protected void addFragment() {
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomeFragment).commit();
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, WelcomeActivity.class);
    }
}
