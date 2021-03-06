package com.spade.mazda.ui.welcome.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.activity.RegistrationActivity;
import com.spade.mazda.ui.authentication.view.activity.ServerLoginActivity;
import com.spade.mazda.ui.main.MainActivity;
import com.spade.mazda.ui.welcome.view.WelcomeView;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 11/22/17.
 */

public class WelcomePresenterImpl implements WelcomePresenter {

    private WelcomeView welcomeView;
    private Context context;
    private DataSource dataSource = DataSource.getInstance();

    public WelcomePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(WelcomeView view) {
        this.welcomeView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getIntroSlides() {
        welcomeView.showLoading();
        ApiHelper.getIntroSlides(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(introResponse -> {
                    welcomeView.hideLoading();
                    welcomeView.showSlides(introResponse.getData());
                }, throwable -> {
                    if (throwable != null)
                        welcomeView.showMessage(throwable.getMessage());
                });
    }


    @Override
    public void navigateToRegister() {
        dataSource.setRegisterSource(DataSource.NORMAL_REGISTER);
        welcomeView.finish();
        context.startActivity(RegistrationActivity.getLaunchIntent(context));
    }

    @Override
    public void navigateToLogin() {
        dataSource.setLoginSource(DataSource.NORMAL_LOGIN);
        welcomeView.finish();
        context.startActivity(ServerLoginActivity.getLaunchIntent(context));
    }

    @Override
    public void navigateToMainScreen() {
        welcomeView.finish();
        context.startActivity(MainActivity.getLaunchIntent(context));
    }
}
