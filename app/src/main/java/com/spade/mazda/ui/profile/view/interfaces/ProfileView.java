package com.spade.mazda.ui.profile.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.profile.model.History;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/20/17.
 */

public interface ProfileView extends BaseView {

    void updateUI(User user);

    void setCarModel(CarModel carModel);

    void setCarColor(String carColor);

    void setCarYear(String carYear);

    void showHistory(List<History> historyList);

}
