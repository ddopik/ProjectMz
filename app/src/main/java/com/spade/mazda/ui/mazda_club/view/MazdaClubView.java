package com.spade.mazda.ui.mazda_club.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface MazdaClubView extends BaseView {
    void showProgressDialog();

    void hideProgressDialog();

    void showMazdaClubsTiers(MazdaClubData mazdaClubData);
}
