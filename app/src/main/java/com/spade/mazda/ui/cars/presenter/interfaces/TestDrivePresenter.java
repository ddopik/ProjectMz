package com.spade.mazda.ui.cars.presenter.interfaces;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface TestDrivePresenter extends BasePresenter<TestDriveView> {

    void requestTest();

    void getCities(List<Branch> branches);

    void getShowRooms();

    void showCarModelsDialog(FragmentManager fragmentManager);

    void showBranchesDialog(FragmentManager fragmentManager);

    void showCitiesDialog(FragmentManager fragmentManager);

    void showDatePicker(FragmentManager fragmentManager);

    boolean dataIsValid();
}
