package com.spade.mazda.realm;

import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.authentication.model.UserModel;
import com.spade.mazda.ui.cars.model.realm_models.CarModel;
import com.spade.mazda.ui.cars.model.realm_models.CarYear;
import com.spade.mazda.ui.cars.model.realm_models.ModelTrim;
import com.spade.mazda.ui.cars.model.realm_models.TrimColor;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Ayman Abouzeid on 6/13/17.
 */

public class RealmDbImpl implements RealmDbHelper {
    public RealmDbImpl() {
    }


    @Override
    public void saveUser(UserModel userModel, String userToken) {
        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.beginTransaction();

        User user = new User();
        user.setUserId(userModel.getId());
        user.setUserEmail(userModel.getEmail());
        user.setUserName(userModel.getName());
        user.setUserPhone(userModel.getMobileNumber());
        user.setBirthDate(userModel.getBirthDate());
        user.setNationalId(userModel.getNationalId());
        user.setNationalIdFrontImage(userModel.getNationalIdFrontImage());
        user.setNationalIdBackImage(userModel.getNationalIdBackImage());
        user.setConfirmationCode(userModel.getConfirmationCode());
        user.setForgetCode(userModel.getForgetCode());
        user.setImage(userModel.getImage());
        user.setIsAdmin(userModel.getIsAdmin());
        user.setIsBlocked(userModel.getIsBlocked());
        user.setIsVerified(userModel.getIsVerified());
        user.setUserToken(userToken);
        user.setCarModel(userModel.getCarModel());
        user.setCarYear(userModel.getCarYear());
        user.setCarColor(userModel.getCarColor());
        user.setCarTrim(userModel.getCarTrim());
        user.setChassis(userModel.getChassis());
        user.setMotor(userModel.getMotor());
        user.setCreatedAt(userModel.getCreatedAt());
        user.setUpdatedAt(userModel.getUpdatedAt());

        realmInstance.copyToRealmOrUpdate(user);
        realmInstance.commitTransaction();
        realmInstance.close();
    }

    @Override
    public void saveCars(List<com.spade.mazda.ui.cars.model.CarModel> carModels) {
        for (com.spade.mazda.ui.cars.model.CarModel carModel : carModels) {

        }
    }

    @Override
    public void updateUserData(String firstName, String lastName, String phoneNumber,
                               String emailAddress, String address, String userId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = new User();
        user.setUserEmail(emailAddress);
        user.setUserName(firstName);
//        user.setLastName(lastName);
//        user.setUserAddress(address);
        user.setUserPhone(phoneNumber);
//        user.setUserId(userId);
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteUser(String userId) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).equalTo("userId", userId).findFirst();
        realm.beginTransaction();
        user.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public List<CarModel> getCarModels() {
        Realm realm = Realm.getDefaultInstance();
        List<CarModel> carModels = realm.where(CarModel.class).findAll();
        realm.close();
        return carModels;
    }

    @Override
    public List<CarYear> getCarYears(int carId) {
        Realm realm = Realm.getDefaultInstance();
        List<CarYear> carYearList = realm.where(CarModel.class).equalTo("carModelId", carId).findFirst().getCarYears();
        realm.close();
        return carYearList;
    }

    @Override
    public List<ModelTrim> getCarTrims(int yearId) {
        Realm realm = Realm.getDefaultInstance();
        List<ModelTrim> modelTrims = realm.where(CarYear.class).equalTo("yearID", yearId).findFirst().getModelTrims();
        realm.close();
        return modelTrims;
    }

    @Override
    public List<TrimColor> getCarColors(int trimId) {
        Realm realm = Realm.getDefaultInstance();
        List<TrimColor> trimColors = realm.where(ModelTrim.class).equalTo("trimId", trimId).findFirst().getTrimColors();
        realm.close();
        return trimColors;
    }


    @Override
    public User getUser(String userId) {
        Realm realm = Realm.getDefaultInstance();
        realm.refresh();
        return realm.where(User.class).equalTo("userId", userId).findFirst();
    }


}
