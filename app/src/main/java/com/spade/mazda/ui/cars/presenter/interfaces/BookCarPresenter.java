package com.spade.mazda.ui.cars.presenter.interfaces;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface BookCarPresenter extends BasePresenter<BookCarView> {
    void bookCar(String name, String mobileNumber, String modelID, String yearId, String trimId, String showRoomID);

    void bookCar();

    void setName(String name);

    void setMobileNumber(String mobileNumber);

    void getCarModel(int carID);

    void getUser();

    void getCarModels();

    void getShowRooms();

    void showCarModelsDialog(FragmentManager fragmentManager);

    void showCarYearsDialog(FragmentManager fragmentManager);

    void showCarTrimsDialog(FragmentManager fragmentManager);

    void showBranches(FragmentManager fragmentManager, List<Branch> branches);

    boolean isDataValid();
}
