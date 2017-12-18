package com.spade.mazda.ui.cars.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public interface DriveFinanceView extends BaseView {

    void showCarYears(List<CarYear> carYears);

    void showPrograms(List<Program> programs);

    void showNumberOfYears();

    void showMonthlyInstallment(String value);
}
