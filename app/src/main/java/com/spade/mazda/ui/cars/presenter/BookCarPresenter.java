package com.spade.mazda.ui.cars.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public interface BookCarPresenter extends BasePresenter<BookCarView> {
    void bookCar(String name, String mobileNumber, String modelID, String yearId, String trimId, String showRoomID);

    void getCarModel(int carID);

    void getUser();

    void getCarModels();

    void getShowRooms();
}
