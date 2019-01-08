package com.spade.mazda.ui.welcome.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.welcome.view.WelcomeView;

/**
 * Created by Ayman Abouzeid on 11/22/17.
 */

public interface WelcomePresenter extends BasePresenter<WelcomeView> {

    void getIntroSlides();

    void navigateToRegister();

    void navigateToLogin();

    void navigateToMainScreen();
}
