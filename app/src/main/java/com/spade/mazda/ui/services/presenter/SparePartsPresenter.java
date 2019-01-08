package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.SparePartsView;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public interface SparePartsPresenter extends BasePresenter<SparePartsView> {

    void getCarModels();

    void getSpareParts(String trimId);
}
