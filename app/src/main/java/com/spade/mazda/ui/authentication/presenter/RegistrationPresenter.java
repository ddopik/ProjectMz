package com.spade.mazda.ui.authentication.presenter;

import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;

import java.io.File;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public interface RegistrationPresenter extends BasePresenter<RegistrationView> {
    void saveFirstStepData(String name, String email, String password, String phoneNumber, String birthDate);

    void pickImageFromCamera(BaseFragment baseFragment);

    void pickImageFromGallery(BaseFragment baseFragment);

    void register(String appLang, String chassisString, String motorString, String nationalIdString,
                  int modelId, int yearId, int trimId, int colorId, File... imageFiles);

    void getCars(String appLang);

    void getCarModels();

    int getModelPosition();

    void setModelPosition(int position);

    int getYearPosition();

    void setYearPosition(int yearPosition);

    int getTrimPosition();

    void setTrimPosition(int trimPosition);

    int getColorPosition();

    void setColorPosition(int colorPosition);

    String getChassisString();

    void setChassisString(String chassisString);

    String getMotorString();

    void setMotorString(String motorString);

    String getNationalIDString();

    void setNationalIDString(String nationalIDString);

    List<File> getFiles();

    void setFiles(List<File> files);

}
