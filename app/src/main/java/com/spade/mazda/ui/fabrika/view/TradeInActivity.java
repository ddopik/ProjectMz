package com.spade.mazda.ui.fabrika.view;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.spade.mazda.R;
import com.spade.mazda.base.ToolBarBaseActivity;

/**
 * Created by Ayman Abouzeid on 11/15/17.
 */

public class TradeInActivity extends ToolBarBaseActivity {

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
        setTitle(R.string.trade_in);
    }

    @Override
    protected void addFragment() {
        TradeInFragment tradeInFragment = new TradeInFragment();
        showFragment(tradeInFragment);
    }


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, TradeInActivity.class);
    }
}
