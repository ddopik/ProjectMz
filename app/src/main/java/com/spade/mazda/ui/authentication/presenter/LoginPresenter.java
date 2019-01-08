package com.spade.mazda.ui.authentication.presenter;

import com.spade.mazda.ui.authentication.view.interfaces.LoginView;
import com.spade.mazda.base.BasePresenter;


/**
 * Created by Ayman Abouzeid on 6/12/17.
 */

public interface LoginPresenter extends BasePresenter<LoginView> {

    void loginAsGuest();

    void serverLogin(String appLang, String email, String password);

    void navigateToRegister();
}
