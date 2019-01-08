package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.FinanceView;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface FinancePresenter extends BasePresenter<FinanceView> {
    void getOffers();

    void getPrograms();
}
