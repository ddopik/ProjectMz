package com.spade.mazda.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.spade.mazda.cars.model.ProductsResponse;
import com.spade.mazda.find_us.model.BranchesResponse;
import com.spade.mazda.find_us.model.CitiesResponse;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ApiHelper {

    private static final String BASE_URL = "http://dev.spade.studio/mazda-mobile/public/api/v1/";
    private static final String PRODUCTS_LIST_URL = BASE_URL + "cars";
    private static final String CITIES_LIST_URL = BASE_URL + "city";
    private static final String BRANCHES_URL = BASE_URL + "branches/{type}";
    private static final String LANG_PATH_PARAM = "{lang}";
    private static final String BRANCH_TYPE_PARAM = "{type}";

    public static Observable<ProductsResponse> getCarModels(String appLang) {
        return Rx2AndroidNetworking.get(PRODUCTS_LIST_URL)
                .build()
                .getObjectObservable(ProductsResponse.class);
    }

    public static Observable<CitiesResponse> getCities(String appLang) {
        return Rx2AndroidNetworking.get(CITIES_LIST_URL)
                .build()
                .getObjectObservable(CitiesResponse.class);
    }

    public static Observable<BranchesResponse> getBranches(String appLang, String branchType) {
        return Rx2AndroidNetworking.get(BRANCHES_URL)
                .addPathParameter(BRANCH_TYPE_PARAM, branchType)
                .build()
                .getObjectObservable(BranchesResponse.class);
    }
}
