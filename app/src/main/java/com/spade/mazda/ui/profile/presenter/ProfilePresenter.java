package com.spade.mazda.ui.profile.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.profile.view.interfaces.ProfileView;

/**
 * Created by Ayman Abouzeid on 12/20/17.
 */

public interface ProfilePresenter extends BasePresenter<ProfileView> {

    void getUserData();

    void getUserCarHistory(int userID);

    void getCarDetails(int carID);
}
