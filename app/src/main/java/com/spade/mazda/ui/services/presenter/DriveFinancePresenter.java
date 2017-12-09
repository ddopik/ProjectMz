package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.DriveFinanceView;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface DriveFinancePresenter extends BasePresenter<DriveFinanceView> {
    void getOffers();

    void getPrograms();
}
