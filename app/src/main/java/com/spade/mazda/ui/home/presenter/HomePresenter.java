package com.spade.mazda.ui.home.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.home.view.HomeView;

/**
 * Created by Ayman Abouzeid on 12/6/17.
 */

public interface HomePresenter extends BasePresenter<HomeView> {

    void getOffers();
}
