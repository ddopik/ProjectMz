package com.spade.mazda.base;

import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.services.model.ServicesLocation;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 12/5/17.
 */

public class DataSource {
    private static DataSource ourInstance = new DataSource();
    private List<CarModel> carModelList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<ServicesLocation> servicesLocations = new ArrayList<>();

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
}
