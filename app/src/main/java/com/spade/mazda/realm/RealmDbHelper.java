package com.spade.mazda.realm;

import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.authentication.model.UserModel;
import com.spade.mazda.ui.cars.model.realm_models.CarModel;
import com.spade.mazda.ui.cars.model.realm_models.CarYear;
import com.spade.mazda.ui.cars.model.realm_models.ModelTrim;
import com.spade.mazda.ui.cars.model.realm_models.TrimColor;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 6/13/17.
 */

public interface RealmDbHelper {

    void saveUserOrUpdate(UserModel userModel, String userToken);

    void saveCars(List<com.spade.mazda.ui.cars.model.CarModel> carModels);

//    void updateUserData(String firstName, String lastName, String phoneNumber, String emailAddress, String address, String userId);

//    void updateUserData(UserModel userModel, String userToken);

//    void updateCarData(UserModel userModel, String userToken);

    void deleteUser(String userId);

    List<CarModel> getCarModels();

    List<CarYear> getCarYears(int carId);

    List<ModelTrim> getCarTrims(int yearId);

    List<TrimColor> getCarColors(int trimId);

    User getUser(int userId);

}
