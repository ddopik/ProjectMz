package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.services.model.Kilometer;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface PeriodicView extends BaseView {

    void showKilometers(List<Kilometer> kilometers);

    void showServiceCenters(List<Branch> branches);

    void setDate(String date);

    void showProgressDialog();

    void hideProgressDialog();
}
