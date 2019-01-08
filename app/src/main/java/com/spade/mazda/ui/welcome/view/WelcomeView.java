package com.spade.mazda.ui.welcome.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.welcome.model.IntroSlide;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/22/17.
 */

public interface WelcomeView extends BaseView {
    void finish();

    void showLoading();

    void hideLoading();

    void showSlides(List<IntroSlide> introSlides);
}
