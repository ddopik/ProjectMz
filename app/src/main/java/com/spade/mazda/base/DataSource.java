package com.spade.mazda.base;

import android.content.Context;

import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.services.model.ServicesLocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 12/5/17.
 */

public class DataSource {
    private static DataSource ourInstance = new DataSource();

    public static final int DIALOG_LOGIN = 1001;
    public static final int NORMAL_LOGIN = 1002;

    public static final int DIALOG_REGISTER = 1003;
    public static final int NORMAL_REGISTER = 1004;

    public static final int REGISTER_ACTIVATION = 1005;
    public static final int LOGIN_ACTIVATION = 1006;

    private List<CarModel> carModelList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<ServicesLocation> servicesLocations = new ArrayList<>();
    private int loginSource, registerSource, activationSource;

    public static DataSource getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataSource();
        }
        return ourInstance;
    }

    public List<CarModel> getCarModelList() {
        return carModelList;
    }

    public void setCarModelList(List<CarModel> carModelList) {
        this.carModelList = carModelList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<ServicesLocation> getServicesLocations() {
        return servicesLocations;
    }

    public void setServicesLocations(List<ServicesLocation> servicesLocations) {
        this.servicesLocations = servicesLocations;
    }

    public int getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(int loginSource) {
        this.loginSource = loginSource;
    }

    public int getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(int registerSource) {
        this.registerSource = registerSource;
    }

    public int getActivationSource() {
        return activationSource;
    }

    public void setActivationSource(int activationSource) {
        this.activationSource = activationSource;
    }

    public Observable<List<ServicesLocation>> getServiceLocationByLocation(int locationID) {
        return Observable.create(e -> {
            List<ServicesLocation> servicesLocationList = new ArrayList<>();
            for (ServicesLocation servicesLocation : getServicesLocations()) {
                if (servicesLocation.getLocation().getId() == locationID) {
                    servicesLocationList.add(servicesLocation);
                }
            }
            e.onNext(servicesLocationList);
            e.onComplete();
        });
    }

    public Observable<CarModel> getCarModel(int carID) {
        return Observable.create(e -> {
            for (CarModel carModel : getCarModelList()) {
                if (carModel.getCarModelId() == carID) {
                    e.onNext(carModel);
                    e.onComplete();
                }
            }
        });
    }

    public int getCarPosition(CarModel carModel) {
        return getCarModelList().indexOf(carModel);
    }

//    public Observable<Integer> getCarTrimPosition(CarYear carYear1, int trimID) {
//        return Observable.create(e -> {
//            CarYear carYear = new CarYear();
//            for (CarYear carYearModel : carYear1.getCarYears()) {
//                if (carYearModel.getYearID() == trimID) {
//                    carYear = carYearModel;
//                }
//            }
//            e.onNext(carYear1.getCarYears().indexOf(carYear));
//            e.onComplete();
//        });
//    }
//    public Observable<Integer> getCarYearPosition(CarModel carModel, int yearID) {
//        return Observable.create(e -> {
//            CarYear carYear = new CarYear();
//            for (CarYear carYearModel : carModel.getCarYears()) {
//                if (carYearModel.getYearID() == yearID) {
//                    carYear = carYearModel;
//                }
//            }
//            e.onNext(carModel.getCarYears().indexOf(carYear));
//            e.onComplete();
//        });
//    }
//    public Observable<Integer> getCarYearPosition(CarModel carModel, int yearID) {
//        return Observable.create(e -> {
//            CarYear carYear = new CarYear();
//            for (CarYear carYearModel : carModel.getCarYears()) {
//                if (carYearModel.getYearID() == yearID) {
//                    carYear = carYearModel;
//                }
//            }
//            e.onNext(carModel.getCarYears().indexOf(carYear));
//            e.onComplete();
//        });
//    }

    public Observable<CarYear> getCarYearByID(CarModel carModel, int yearID) {
        return Observable.create(e -> {
            for (CarYear carYear : carModel.getCarYears()) {
                if (carYear.getYearID() == yearID) {
                    e.onNext(carYear);
                    e.onComplete();
                }
            }
        });
    }

    public Observable<ModelTrim> getCarTrimByID(CarYear carYear, int trimID) {
        return Observable.create(e -> {
            for (ModelTrim modelTrim : carYear.getModelTrims()) {
                if (modelTrim.getTrimId() == trimID) {
                    e.onNext(modelTrim);
                    e.onComplete();
                }
            }
        });
    }

    public Observable<TrimColor> getCarColorByID(ModelTrim modelTrim, int colorID) {
        return Observable.create(e -> {
            for (TrimColor trimColor : modelTrim.getTrimColors()) {
                if (trimColor.getColorID() == colorID) {
                    e.onNext(trimColor);
                    e.onComplete();
                }
            }
        });
    }

    public Observable<List<Branch>> getBranchByCityId(List<Branch> branches, int cityId) {
        return Observable.create(e -> {
            List<Branch> branchList = new ArrayList<>();
            for (Branch branch : branches) {
                if (branch.getBranchCity().getCityId() == cityId) {
                    branchList.add(branch);
                }
            }
            e.onNext(branchList);
            e.onComplete();
        });
    }

    public static Observable<List<City>> getCitiesList(List<Branch> branchesList, Context context, boolean addDummyCity) {
        return Observable.create(e -> {
            List<City> cities = new ArrayList<>();
            Set<City> citiesSet = new HashSet<>();
            for (Branch branch : branchesList) {
                citiesSet.add(branch.getBranchCity());
            }
            if (addDummyCity) {
                City city = new City();
                city.setCityId(-1);
                city.setCityName(context.getString(R.string.select_city));
                cities.add(city);
            }
            cities.addAll(citiesSet);
            e.onNext(cities);
            e.onComplete();
        });
    }
}
