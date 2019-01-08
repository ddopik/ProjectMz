package com.spade.mazda.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void init();

    protected abstract void addFragment();

}
