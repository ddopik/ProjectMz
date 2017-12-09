package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.home.model.Offer;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public interface DriveFinanceView extends BaseView {

    void showProgramsLoading();

    void hideProgramsLoading();

    void showOffers(List<Offer> offers);

    void showPrograms(List<Program> programs);
}
