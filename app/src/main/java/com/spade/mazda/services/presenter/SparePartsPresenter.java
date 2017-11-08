package com.spade.mazda.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.services.view.SparePartsView;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public interface SparePartsPresenter extends BasePresenter<SparePartsView> {

    void getSpareParts(String appLang);
}
