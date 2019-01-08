package com.spade.mazda.ui.fabrika.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.fabrika.view.TradeInView;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface TradeInPresenter extends BasePresenter<TradeInView> {

    void getBrands();

    void getModels();

    void getBranches();

    void sendRequest(String name, String email, String phone, int myCarBrandId, int myCarModelId, int requestedCarBrandId, int requestedCarModelId, int branchId);
}
