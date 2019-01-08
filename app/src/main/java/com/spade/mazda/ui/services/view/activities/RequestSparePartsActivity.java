package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.services.view.fragments.RequestSparePartsFragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class RequestSparePartsActivity extends ToolBarBaseActivity {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        init();
//    }


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
        setTitle(R.string.spare_parts);
    }

    @Override
    protected void addFragment() {
        RequestSparePartsFragment requestSparePartsFragment = new RequestSparePartsFragment();
        showFragment(requestSparePartsFragment);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, requestSparePartsFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, RequestSparePartsActivity.class);
    }
}
