package com.spade.mazda.ui.authentication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.ui.authentication.view.fragment.ServerLoginFragment;


/**
 * Created by Ayman Abouzeidd on 6/12/17.
 */

public class ServerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addFragment();
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


    private void addFragment() {
        ServerLoginFragment serverLoginFragment = new ServerLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, serverLoginFragment).commit();
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, ServerLoginActivity.class);
    }
}
