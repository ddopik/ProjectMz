package com.spade.mazda.ui.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.main.MainActivity;
import com.spade.mazda.ui.welcome.view.WelcomeActivity;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY_MILLIS = 1000;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_splash);
//        counterToNavigate();
        init();
    }

    private void init() {
        progressBar = findViewById(R.id.progress_bar);
        getCarModels();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    private void navigate() {
        if (PrefUtils.isLoggedIn(this)) {
            navigateToMainScreen();
        } else {
            navigateToWelcomeScreen();
        }
    }

    private void counterToNavigate() {
        Handler handler = new Handler();
        Runnable runnable = this::navigate;
        handler.postDelayed(runnable, DELAY_MILLIS);
    }


    private void navigateToWelcomeScreen() {
        finish();
        startActivity(WelcomeActivity.getLaunchIntent(this));
    }

    private void navigateToMainScreen() {
        finish();
        startActivity(MainActivity.getLaunchIntent(this));
    }

    @SuppressLint("CheckResult")
    private void getCarModels() {
        String appLang = PrefUtils.getAppLang(this);
        DataSource dataSource = DataSource.getInstance();
        progressBar.setVisibility(View.VISIBLE);
        ApiHelper.getCarModels(appLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productsResponse -> {
                    progressBar.setVisibility(View.GONE);
                    if (productsResponse != null && productsResponse.getCarModels() != null) {
                        dataSource.setCarModelList(productsResponse.getCarModels());
                        navigate();
                    }
                }, throwable -> {
                    progressBar.setVisibility(View.GONE);
                });
    }
}
