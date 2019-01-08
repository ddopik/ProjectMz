package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.Places360View;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface Places360Presenter extends BasePresenter<Places360View> {
    void getPlaces();
}
