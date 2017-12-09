package com.spade.mazda.ui.home.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.home.model.Offer;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public interface HomeView extends BaseView {
    void showLoading();

    void hideLoading();

    void showOffers(List<Offer> offerList);
}
