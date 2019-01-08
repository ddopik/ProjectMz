package com.spade.mazda.ui.profile.presenter;

import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.profile.view.interfaces.EditProfileView;

import java.io.File;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/20/17.
 */

public interface EditProfilePresenter extends BasePresenter<EditProfileView> {

    void setName(String name);

    void setMobileNumber(String mobileNumber);

    void setBirthDate(String birthDate);

    void setEmail(String email);

    void setNationalId(String nationalID);

    void getUserData();

    void getCarData();

//    void getCarDetails(int carID);

    void pickImageFromCamera(BaseFragment baseFragment);

    void pickImageFromGallery(BaseFragment baseFragment);

    void setFiles(List<File> files);

    void setUserImage(File userImageFile);

    void editProfile();

    boolean isDataValid();
}
