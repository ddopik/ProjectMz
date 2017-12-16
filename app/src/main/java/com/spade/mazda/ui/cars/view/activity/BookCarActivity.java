package com.spade.mazda.ui.cars.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseActivity;
import com.spade.mazda.ui.cars.view.fragments.BookCarFragment;
import com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment;

import static com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment.EXTRA_CAR_ID;
import static com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment.EXTRA_TRIM_POSITION;
import static com.spade.mazda.ui.cars.view.fragments.CarDetailsFragment.EXTRA_YEAR_POSITION;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class BookCarActivity extends BaseActivity {

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
        addFragment();
        setTitle(R.string.book_now);
    }

    @Override
    protected void addFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CAR_ID, getIntent().getIntExtra(EXTRA_CAR_ID, 1));
        bundle.putInt(EXTRA_YEAR_POSITION, getIntent().getIntExtra(EXTRA_YEAR_POSITION, 1));
        bundle.putInt(EXTRA_TRIM_POSITION, getIntent().getIntExtra(EXTRA_TRIM_POSITION, 1));
        BookCarFragment bookCarFragment = new BookCarFragment();
        bookCarFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, bookCarFragment).commit();
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, BookCarActivity.class);
    }
}
