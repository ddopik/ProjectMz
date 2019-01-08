package com.spade.mazda.ui.mazda_club.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.mazda_club.view.MazdaClubView;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface MazdaClubPresenter extends BasePresenter<MazdaClubView> {
    void getMazdaClubTiers();

    void subscribe();
}
