package com.spade.mazda.ui.profile.presenter;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.ui.profile.view.interfaces.EditCarView;

/**
 * Created by Ayman Abouzeid on 12/24/17.
 */

public interface EditCarPresenter extends BasePresenter<EditCarView> {

    void getUser();

    CarModel getCarModel();

    CarYear getCarYear();

    ModelTrim getModelTrim();

    TrimColor getTrimColor();

    void setCarModel(CarModel carModel);

    void setCarYear(CarYear carYear);

    void setModelTrim(ModelTrim modelTrim);

    void setTrimColor(TrimColor trimColor);

    void setChassisString(String chassis);

    void setMotorString(String motor);

    void showCarModelsDialog(FragmentManager fragmentManager);

    void showCarYearsDialog(FragmentManager fragmentManager);

    void showCarColorsDialog(FragmentManager fragmentManager);

    void showCarTrimsDialog(FragmentManager fragmentManager);

    boolean dataIsValid();

    void edit();

}
