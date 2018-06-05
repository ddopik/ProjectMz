package com.spade.mazda.network;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rx2androidnetworking.Rx2ANRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.spade.mazda.ui.authentication.model.LoginResponse;
import com.spade.mazda.ui.authentication.model.RegistrationResponse;
import com.spade.mazda.ui.authentication.model.ValidateMazdaLoginResponse;
import com.spade.mazda.ui.cars.model.CarDetailsResponse;
import com.spade.mazda.ui.cars.model.ProductsResponse;
import com.spade.mazda.ui.fabrika.model.FabricaResponse;
import com.spade.mazda.ui.find_us.model.BranchesResponse;
import com.spade.mazda.ui.find_us.model.CitiesResponse;
import com.spade.mazda.ui.home.model.OffersResponse;
import com.spade.mazda.ui.mazda_club.model.MazdaClubResponse;
import com.spade.mazda.ui.profile.model.History;
import com.spade.mazda.ui.services.model.KilometersResponse;
import com.spade.mazda.ui.services.model.LocationsResponse;
import com.spade.mazda.ui.services.model.ProgramsResponse;
import com.spade.mazda.ui.services.model.ServicesLocationsResponse;
import com.spade.mazda.ui.services.model.SparePartsResponse;
import com.spade.mazda.ui.services.model.ThreeSixtyResponse;
import com.spade.mazda.ui.welcome.model.IntroResponse;
import com.spade.mazda.utils.ErrorUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class ApiHelper {

    private static final String BASE_URL = "http://dev.spade.studio/mazda-mobile/public/api/v1/{lang}/";
    private static final String FABRICA_BASE_URL = "http://fabrikaegypt.com/api/{lang}/v1/cars/";
    private static final String VALIDATE_LOGIN = "https://gbmazdaapp.azurewebsites.net/rest/call/validateLogin";
    private static final String GET_HISTORY_URL =   "https://gbmazdaapp.azurewebsites.net/rest/call/jobCar";

    private static final String PRODUCTS_LIST_URL = BASE_URL + "cars";
    private static final String CITIES_LIST_URL = BASE_URL + "city";
    private static final String BRANCHES_URL = BASE_URL + "branches/{type}";
    private static final String SPARE_PARTS_URL = BASE_URL + "spareParts/{trim_id}";
    private static final String REGISTER_URL = BASE_URL + "register";
    private static final String LOGIN_URL = BASE_URL + "authenticate";
    private static final String ACTIVATE_URL = BASE_URL + "email/activate";
    private static final String EDIT_PROFILE_URL = BASE_URL + "profile/edit";
    private static final String CAR_DETAILS_URL = BASE_URL + "cars/{car_id}/{trim_id}";
    private static final String OFFERS_URL = BASE_URL + "offers";
    private static final String INTRO_SLIDER_URL = BASE_URL + "slider";
    private static final String DRIVE_FINANCE_URL = BASE_URL + "finance";
    private static final String THREE_SIXTY_URL = BASE_URL + "360";
    private static final String SERVICES_LOCATIONS_URL = BASE_URL + "afterSales/{category_id}";
    private static final String LOCATIONS_URL = BASE_URL + "locations";
    private static final String BOOK_CAR_URL = BASE_URL + "cars/book";
    private static final String REQUEST_TEST_DRIVE_URL = BASE_URL + "testDrive/request";
    private static final String REQUEST_SPARE_PART_URL = BASE_URL + "spareParts/request";
    private static final String KILOMETERS_URL = BASE_URL + "kilometerServices";
    public static final String FABRICA_BRANDS_URL = FABRICA_BASE_URL + "brands";
    public static final String FABRICA_MODELS_URL = FABRICA_BASE_URL + "models";
    public static final String FABRICA_BRANCHES_URL = FABRICA_BASE_URL + "branches";
    private static final String FABRICA_REQUEST_TRADE_IN = FABRICA_BASE_URL + "trade";
    private static final String MAZDA_CLUB_URL = BASE_URL + "mazdaClub";
    private static final String ADD_HISTORY_URL = BASE_URL + "history";
//    private static final String GET_HISTORY_URL = BASE_URL + "profile/history";
    private static final String INTERESTED_URL = BASE_URL + "interested";
    private static final String LANG_PATH_PARAM = "lang";
    private static final String CHASSIS_PATH_PARAM = "chassis";
    private static final String MOTOR_PATH_PARAM = "motor";
    private static final String NATIONAL_ID_PATH_PARAM = "NationalID";
    private static final String BRANCH_TYPE_PARAM = "type";
    private static final String CAR_ID_PATH_PARAM = "car_id";
    private static final String TRIM_ID_PATH_PARAM = "trim_id";
    private static final String MOTOR_NO = "motorNo";
    private static final String CHASSIS_NO = "chassisNo";
    private static final String AUTH_TOKEN = "Authorization";
    private static final String BEARER = "bearer";
    private static final String LOCATION_CATEGORY_PATH_PARAM = "category_id";
    public static final String AFTER_SALES_LOCATIONS_PARAM = "1";
    public static final String FIXOLOGY_LOCATIONS_PARAM = "2";
    public static final int NOT_ACTIVATED = 402;

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

    public static Observable<KilometersResponse> getKilometers(String appLang) {
        return Rx2AndroidNetworking.get(KILOMETERS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(KilometersResponse.class);
    }

    public static Observable<SparePartsResponse> getSpareParts(String appLang, String trimId) {
        Rx2ANRequest.GetRequestBuilder getRequestBuilder = Rx2AndroidNetworking.get(SPARE_PARTS_URL);
        if (trimId != null) {
            getRequestBuilder.addPathParameter(TRIM_ID_PATH_PARAM, trimId);
        } else {
            getRequestBuilder.addPathParameter(TRIM_ID_PATH_PARAM, "");
        }
        return getRequestBuilder
                .addPathParameter(LANG_PATH_PARAM, appLang)
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

    public static Observable<ServicesLocationsResponse> getServicesLocation(String appLang, String locationType) {
        return Rx2AndroidNetworking.get(SERVICES_LOCATIONS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addPathParameter(LOCATION_CATEGORY_PATH_PARAM, locationType)
                .build()
                .getObjectObservable(ServicesLocationsResponse.class);
    }

    public static Observable<LocationsResponse> getLocations(String appLang) {
        return Rx2AndroidNetworking.get(LOCATIONS_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(LocationsResponse.class);
    }

    public static Observable<FabricaResponse> getFabricaData(String appLang, String url) {
        return Rx2AndroidNetworking.get(url)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addHeaders("X-Client-Id", "Ohg4wutu")
                .addHeaders("X-Client-Secret", "chaiheiMoopuo3moose7xaichaR4ph")
                .addHeaders("Content-Type", "application/json")
                .build()
                .getObjectObservable(FabricaResponse.class);
    }

    public static Observable<MazdaClubResponse> getMazdaClubTiers(String appLang) {
        return Rx2AndroidNetworking.get(MAZDA_CLUB_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .build()
                .getObjectObservable(MazdaClubResponse.class);
    }

//    public static Observable<HistoryResponse> getHistory(String appLang, String token) {
//        return Rx2AndroidNetworking.get(GET_HISTORY_URL)
//                .addPathParameter(LANG_PATH_PARAM, appLang)
//                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
//                .build()
//                .getObjectObservable(HistoryResponse.class);
//    }
    public static Observable<List<History>> getHistory(String motorNo, String chassisNo) {
        return Rx2AndroidNetworking.get(GET_HISTORY_URL)
                .addQueryParameter(MOTOR_NO,motorNo)
                .addQueryParameter(CHASSIS_NO,chassisNo)
                .build()
                .getObjectListObservable(History.class);
    }

    public static Observable<LoginResponse> loginUser(String appLang, String email, String password) {
        return Rx2AndroidNetworking.post(LOGIN_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getObjectObservable(LoginResponse.class);
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

    public static Observable<LoginResponse> editProfile(String appLang, String token,
                                                        String nationalIdString, String name, String phoneNumber, String birthDate, File userImage, File... imageFiles) {
        Rx2ANRequest.MultiPartBuilder multiPartBuilder = Rx2AndroidNetworking.upload(EDIT_PROFILE_URL);
        if (imageFiles != null && imageFiles.length > 0) {
            multiPartBuilder.addMultipartFile("national_id_front_image", imageFiles[0])
                    .addMultipartFile("national_id_back_image", imageFiles[1]);
        }
        return multiPartBuilder.addPathParameter(LANG_PATH_PARAM, appLang)
                .addHeaders("Content-Type", "multipart/form-data")
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addMultipartParameter("name", name)
                .addMultipartParameter("mobile_number", phoneNumber)
                .addMultipartParameter("birth_date", birthDate)
                .addMultipartParameter("national_id", nationalIdString)
                .addMultipartFile("image", userImage)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    public static Observable<LoginResponse> editCar(String appLang, String token, String chassisString, String motorString,
                                                    int modelId, int yearId, int trimId, int colorId) {
        return Rx2AndroidNetworking.upload(EDIT_PROFILE_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addHeaders("Content-Type", "multipart/form-data")
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addMultipartParameter("car_model", String.valueOf(modelId))
                .addMultipartParameter("car_year", String.valueOf(yearId))
                .addMultipartParameter("chassis", chassisString)
                .addMultipartParameter("motor", motorString)
                .addMultipartParameter("car_trim", String.valueOf(trimId))
                .addMultipartParameter("car_color", String.valueOf(colorId))
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    public static void activateUser(String appLang, String email, String code, ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(ACTIVATE_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("code", code)
                .addBodyParameter("email", email)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(anError.getMessage());
                    }
                });
    }

    public static void bookCar(String appLang, String token, String name, String phone, String modelId, String yearId, String trimId, String branchId
            , ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(BOOK_CAR_URL)
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("name", name)
                .addBodyParameter("number", phone)
                .addBodyParameter("car_model_id", modelId)
                .addBodyParameter("car_trim_id", trimId)
                .addBodyParameter("year_id", yearId)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(ErrorUtils.getErrors(anError));
                    }
                });
    }

    public static void requestDriveTest(String appLang, String token, String modelId, String cityId, String branchId, String date
            , ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(REQUEST_TEST_DRIVE_URL)
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("location_id", branchId)
                .addBodyParameter("car_model_id", modelId)
                .addBodyParameter("city_id", cityId)
                .addBodyParameter("time", date)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(ErrorUtils.getErrors(anError));
                    }
                });
    }

    public static void requestSparePart(String appLang, String token, String sparePartTypeID, String sparePartID, String branchId, String description
            , ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(REQUEST_SPARE_PART_URL)
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("branch_id", branchId)
                .addBodyParameter("spare_part_category_id", sparePartTypeID)
                .addBodyParameter("spare_part_id", sparePartID)
                .addBodyParameter("description", description)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(ErrorUtils.getErrors(anError));
                    }
                });
    }


    public static void addHistory(String appLang, String token, String comment, String date, File imageFile
            , ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.upload(ADD_HISTORY_URL)
                .addHeaders("Content-Type", "multipart/form-data")
                .addHeaders(AUTH_TOKEN, BEARER + " " + token)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addMultipartParameter("comment", comment)
                .addMultipartFile("image", imageFile)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(ErrorUtils.getErrors(anError));
                    }
                });
    }

    public static void interested(String appLang, String name, String phone, String trimId,
                                  String programId, String numberOfYears, String downPayment
            , ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(INTERESTED_URL)
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addBodyParameter("name", name)
                .addBodyParameter("phone", phone)
                .addBodyParameter("number_of_year", numberOfYears)
                .addBodyParameter("program_id", programId)
                .addBodyParameter("car_trim_id", trimId)
                .addBodyParameter("down_payment", downPayment)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(ErrorUtils.getErrors(anError));
                    }
                });
    }

    public static void requestTradeIn(String appLang, JSONObject jsonObject, ApiCallBack apiCallBack) {
        Rx2AndroidNetworking.post(FABRICA_REQUEST_TRADE_IN)
                .addHeaders("X-Client-Id", "Ohg4wutu")
                .addHeaders("X-Client-Secret", "chaiheiMoopuo3moose7xaichaR4ph")
                .addHeaders("Content-Type", "application/json")
                .addPathParameter(LANG_PATH_PARAM, appLang)
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiCallBack.onSuccess();
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiCallBack.onFail(anError.getMessage());
                    }
                });
    }


    public static Observable<ValidateMazdaLoginResponse> validateLogin(String appLang, String chassisNum, String motorNum, String nationalID) {
        return Rx2AndroidNetworking.get(VALIDATE_LOGIN)
                .addQueryParameter(CHASSIS_PATH_PARAM, chassisNum)
                .addQueryParameter(MOTOR_PATH_PARAM, motorNum)
                .addQueryParameter(NATIONAL_ID_PATH_PARAM, nationalID)
                .build()
                .getObjectObservable(ValidateMazdaLoginResponse.class);
    }


    public interface ApiCallBack {
        void onSuccess();

        void onFail(String message);
    }


}
