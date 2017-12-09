package com.spade.mazda.network;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.spade.mazda.ui.authentication.model.LoginResponse;
import com.spade.mazda.ui.authentication.model.RegistrationResponse;
import com.spade.mazda.ui.cars.model.CarDetailsResponse;
import com.spade.mazda.ui.cars.model.ProductsResponse;
import com.spade.mazda.ui.find_us.model.BranchesResponse;
import com.spade.mazda.ui.find_us.model.CitiesResponse;
import com.spade.mazda.ui.home.model.OffersResponse;
import com.spade.mazda.ui.services.model.ProgramsResponse;
import com.spade.mazda.ui.services.model.SparePartsResponse;
import com.spade.mazda.ui.services.model.ThreeSixtyResponse;
import com.spade.mazda.ui.welcome.model.IntroResponse;

import java.io.File;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ApiHelper {

    private static final String BASE_URL = "http://dev.spade.studio/mazda-mobile/public/api/v1/{lang}/";
    private static final String PRODUCTS_LIST_URL = BASE_URL + "cars";
    private static final String CITIES_LIST_URL = BASE_URL + "city";
    private static final String BRANCHES_URL = BASE_URL + "branches/{type}";
    private static final String SPARE_PARTS_URL = BASE_URL + "spareParts/{trim_id}";
    private static final String REGISTER_URL = BASE_URL + "register";
    private static final String LOGIN_URL = BASE_URL + "authenticate";
    private static final String ACTIVATE_URL = BASE_URL + "email/activate";
    private static final String CAR_DETAILS_URL = BASE_URL + "cars/{car_id}/{trim_id}";
    private static final String OFFERS_URL = BASE_URL + "offers";
    private static final String INTRO_SLIDER_URL = BASE_URL + "slider";
    private static final String DRIVE_FINANCE_URL = BASE_URL + "finance";
    private static final String THREE_SIXTY_URL = BASE_URL + "360";
    private static final String LANG_PATH_PARAM = "lang";
    private static final String BRANCH_TYPE_PARAM = "type";
    private static final String CAR_ID_PATH_PARAM = "car_id";
    private static final String TRIM_ID_PATH_PARAM = "trim_id";

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

    public static Observable<SparePartsResponse> getSpareParts(String appLang, String trimId) {
        return Rx2AndroidNetworking.get(SPARE_PARTS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addPathParameter(TRIM_ID_PATH_PARAM, trimId)
                .build()
                .getObjectObservable(SparePartsResponse.class);
    }

    public static Observable<CarDetailsResponse> getCarDetails(String appLang, String carId, String trimId) {
        return Rx2AndroidNetworking.get(CAR_DETAILS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addPathParameter(CAR_ID_PATH_PARAM, carId)
                .addPathParameter(TRIM_ID_PATH_PARAM, trimId)
                .build()
                .getObjectObservable(CarDetailsResponse.class);
    }

    public static Observable<OffersResponse> getOffers(String appLang) {
        return Rx2AndroidNetworking.get(OFFERS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(OffersResponse.class);
    }

    public static Observable<IntroResponse> getIntroSlides(String appLang) {
        return Rx2AndroidNetworking.get(INTRO_SLIDER_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(IntroResponse.class);
    }

    public static Observable<ProgramsResponse> getPrograms(String appLang) {
        return Rx2AndroidNetworking.get(DRIVE_FINANCE_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(ProgramsResponse.class);
    }

    public static Observable<ThreeSixtyResponse> get360(String appLang) {
        return Rx2AndroidNetworking.get(THREE_SIXTY_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(ThreeSixtyResponse.class);
    }

    public static Observable<RegistrationResponse> registerUser(String appLang, String nameString, String emailString, String passwordString, String mobileNumberString,
                                                                String birthDateString, String chassisString, String motorString, String nationalIdString,
                                                                int modelId, int yearId, int trimId, int colorId, File... imageFiles) {
        return Rx2AndroidNetworking.upload(REGISTER_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addHeaders("Content-Type", "multipart/form-data")
                .addMultipartParameter("email", emailString)
                .addMultipartParameter("password", passwordString)
                .addMultipartParameter("name", nameString)
                .addMultipartParameter("mobile_number", mobileNumberString)
                .addMultipartParameter("birth_date", birthDateString)
                .addMultipartParameter("national_id", nationalIdString)
                .addMultipartParameter("car_model", String.valueOf(modelId))
                .addMultipartParameter("car_year", String.valueOf(yearId))
                .addMultipartParameter("chassis", chassisString)
                .addMultipartParameter("motor", motorString)
                .addMultipartParameter("car_trim", String.valueOf(trimId))
                .addMultipartParameter("car_color", String.valueOf(colorId))
                .addMultipartFile("national_id_front_image", imageFiles[0])
                .addMultipartFile("national_id_back_image", imageFiles[1])
                .build()
                .getObjectObservable(RegistrationResponse.class);
    }

    public static void activateUser(String appLang, String code, ActivationActions activationActions) {
        Rx2AndroidNetworking.post(ACTIVATE_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("code", code)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        activationActions.onActivationSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        activationActions.onActivationFail(anError.getMessage());
                    }
                });
    }

    public static Observable<LoginResponse> loginUser(String appLang, String email, String password) {
        return Rx2AndroidNetworking.post(LOGIN_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getObjectObservable(LoginResponse.class);
    }


    public interface ActivationActions {
        void onActivationSuccess();

        void onActivationFail(String message);
    }
}
