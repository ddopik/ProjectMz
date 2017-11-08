package com.spade.mazda.find_us.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.find_us.view.FindUsView;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface FindUsPresenter extends BasePresenter<FindUsView> {
    void setUpViewPager();

    void getCities(String appLang);
}
