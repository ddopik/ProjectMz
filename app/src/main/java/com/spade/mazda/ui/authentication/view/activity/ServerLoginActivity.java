package com.spade.mazda.ui.authentication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;
import com.spade.mazda.ui.authentication.view.fragment.ServerLoginFragment;


/**
 * Created by Ayman Abouzeidd on 6/12/17.
 */

public class ServerLoginActivity extends BaseActivity {

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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void init() {
        addFragment();
        setTitle(R.string.login);
    }

    protected void addFragment() {
        ServerLoginFragment serverLoginFragment = new ServerLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, serverLoginFragment).commit();
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, ServerLoginActivity.class);
    }
}
