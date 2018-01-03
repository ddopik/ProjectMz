package com.spade.mazda.ui.mazda_club.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface MazdaClubView extends BaseView {
    void showProgressDialog();

    void hideProgressDialog();

    void showMazdaClubsTiers(List<MazdaClubData> mazdaClubDataList);
}
