package com.spade.mazda.ui.authentication.presenter;

import android.content.Context;

import com.spade.mazda.ui.authentication.model.LoginResponse;
import com.spade.mazda.ui.authentication.view.interfaces.LoginView;
import com.spade.mazda.ui.authentication.view.activity.RegistrationActivity;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.utils.PrefUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeidd on 6/12/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;
    private Context mContext;
    private RealmDbHelper realmDbHelper;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        setView(loginView);
        mContext = context;
        realmDbHelper = new RealmDbImpl();
    }


    @Override
    public void loginAsGuest() {
        mLoginView.navigateToMainScreen();
    }

    @Override
    public void serverLogin(String appLang, String email, String password) {
        mLoginView.showLoading();
        ApiHelper.loginUser(appLang, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    mLoginView.hideLoading();
                    if (loginResponse != null) {
                        completeLogin(loginResponse);
                        mLoginView.finish();
                        mLoginView.navigate();
                    }
                }, throwable -> {
                    mLoginView.hideLoading();
//                    if (throwable != null) {
////                        ANError anError = (ANError) throwable;
////                        mLoginView.onError(ErrorUtils.getErrors(anError));
//                    }
                });
    }

    @Override
    public void navigateToRegister() {
        mLoginView.finish();
        mContext.startActivity(RegistrationActivity.getLaunchIntent(mContext));
    }


    @Override
    public void setView(LoginView view) {
        mLoginView = view;
    }


    private void completeLogin(LoginResponse response) {
        PrefUtils.setIsLoggedIn(mContext, true);
        realmDbHelper.saveUser(response.getLoginData().getUser(),
                response.getLoginData().getToken());
    }

}
