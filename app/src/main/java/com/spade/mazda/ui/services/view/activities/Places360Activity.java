package com.spade.mazda.ui.services.view.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;
import com.spade.mazda.ui.services.view.fragments.Places360Fragment;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class Places360Activity extends ToolBarBaseActivity {

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
        setTitle(R.string.service_360);
    }

    @Override
    protected void addFragment() {
        Places360Fragment places360Fragment = new Places360Fragment();
        showFragment(places360Fragment);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, places360Fragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, Places360Activity.class);
    }
}
