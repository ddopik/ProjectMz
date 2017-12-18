package com.spade.mazda.ui.cars.presenter.interfaces;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.TestDriveView;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface TestDrivePresenter extends BasePresenter<TestDriveView> {
    void requestTest(String modelID, String cityId, String showRoomID, String date);

    void getCities(List<Branch> branches);

    void getCarModels();

    void getShowRooms();
}
