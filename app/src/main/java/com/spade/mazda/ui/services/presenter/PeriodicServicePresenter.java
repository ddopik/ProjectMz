package com.spade.mazda.ui.services.presenter;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.PeriodicView;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface PeriodicServicePresenter extends BasePresenter<PeriodicView> {
    void getServiceCenters();

    void getKiloMetersServices();

    void openDatePicker(FragmentManager fragmentManager);
}
