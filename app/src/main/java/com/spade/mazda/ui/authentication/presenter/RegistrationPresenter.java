package com.spade.mazda.ui.authentication.presenter;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.authentication.view.fragment.RegistrationFirstStepFragment;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;

import java.io.File;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public interface RegistrationPresenter extends BasePresenter<RegistrationView> {

    //    void saveFirstStepData(String name, String email, String VALIDATE_LOGIN_PASSWORD, String phoneNumber, String birthDate);
    void saveFirstStepData(String chassisID, String motorID, String nationalID, String carModel, String carYear, String carTrim, String carColor);

    void pickImageFromCamera(BaseFragment baseFragment);

    void pickImageFromGallery(BaseFragment baseFragment);

//    void register(String appLang, String chassisString, String motorString, String nationalIdString,
//                  int modelId, int yearId, int trimId, int colorId, File... imageFiles);


    CarModel getCarModel();

    CarYear getCarYear();

    ModelTrim getModelTrim();

    TrimColor getTrimColor();

    void setCarModel(CarModel carModel);

    void setCarYear(CarYear carYear);

    void setModelTrim(ModelTrim modelTrim);

    void setTrimColor(TrimColor trimColor);

    String getChassisString();

    void setChassisString(String chassisString);

    String getMotorString();

    void setMotorString(String motorString);

    String getNationalIDString();

    void setNationalIDString(String nationalIDString);

    List<File> getFiles();

    void setFiles(List<File> files);

    void showCarModelsDialog(FragmentManager fragmentManager);

    void showCarYearsDialog(FragmentManager fragmentManager);

    void showCarColorsDialog(FragmentManager fragmentManager);

    void showCarTrimsDialog(FragmentManager fragmentManager);

    boolean dataIsValid();

    void validateMazdaUserLogin(String AppLang, RegistrationFirstStepFragment.OnNextClicked onNextClicked);
}
