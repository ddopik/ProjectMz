package com.spade.mazda.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.spade.mazda.cars.model.ProductsResponse;
import com.spade.mazda.find_us.model.BranchesResponse;
import com.spade.mazda.find_us.model.CitiesResponse;
import com.spade.mazda.services.model.SparePartsResponse;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ApiHelper {

    private static final String BASE_URL = "http://dev.spade.studio/mazda-mobile/public/api/v1/{lang}/";
    private static final String PRODUCTS_LIST_URL = BASE_URL + "cars";
    private static final String CITIES_LIST_URL = BASE_URL + "city";
    private static final String BRANCHES_URL = BASE_URL + "branches/{type}";
    private static final String SPARE_PARTS_URL = BASE_URL + "spareParts";
    private static final String LANG_PATH_PARAM = "lang";
    private static final String BRANCH_TYPE_PARAM = "type";

    public static Observable<ProductsResponse> getCarModels(String appLang) {
        return Rx2AndroidNetworking.get(PRODUCTS_LIST_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(ProductsResponse.class);
    }

    public static Observable<CitiesResponse> getCities(String appLang) {
        return Rx2AndroidNetworking.get(CITIES_LIST_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(CitiesResponse.class);
    }

    public static Observable<BranchesResponse> getBranches(String appLang, String branchType) {
        return Rx2AndroidNetworking.get(BRANCHES_URL)
                .addPathParameter(BRANCH_TYPE_PARAM, branchType)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(BranchesResponse.class);
    }

    public static Observable<SparePartsResponse> getSpareParts(String appLang) {
        return Rx2AndroidNetworking.get(SPARE_PARTS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(SparePartsResponse.class);
    }
}
