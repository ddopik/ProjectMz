package com.spade.mazda.ui.find_us.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.find_us.view.interfaces.FindUsView;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface FindUsPresenter extends BasePresenter<FindUsView> {
    void setUpViewPager();

    void getCities(String appLang);
}
