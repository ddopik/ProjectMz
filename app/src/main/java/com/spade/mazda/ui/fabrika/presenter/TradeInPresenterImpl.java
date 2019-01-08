package com.spade.mazda.ui.fabrika.presenter;

import android.content.Context;

import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.fabrika.view.TradeInView;
import com.spade.mazda.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class TradeInPresenterImpl implements TradeInPresenter {

    private TradeInView tradeInView;
    private Context context;

    public TradeInPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(TradeInView view) {
        tradeInView = view;
    }

    @Override
    public void getBrands() {
        tradeInView.showLoading();
        ApiHelper.getFabricaData(PrefUtils.getAppLang(context), ApiHelper.FABRICA_BRANDS_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fabricaResponse -> {
                    tradeInView.hideLoading();
                    tradeInView.showBrands(fabricaResponse.getData());
                }, throwable -> {
                    tradeInView.hideLoading();
                    if (throwable != null)
                        tradeInView.showMessage(throwable.getMessage());
                });
    }

    @Override
    public void getModels() {
        tradeInView.showLoading();
        ApiHelper.getFabricaData(PrefUtils.getAppLang(context), ApiHelper.FABRICA_MODELS_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fabricaResponse -> {
                    tradeInView.hideLoading();
                    tradeInView.showModels(fabricaResponse.getData());
                }, throwable -> {
                    tradeInView.hideLoading();
                    if (throwable != null)
                        tradeInView.showMessage(throwable.getMessage());
                });
    }

    @Override
    public void getBranches() {
        tradeInView.showLoading();
        ApiHelper.getFabricaData(PrefUtils.getAppLang(context), ApiHelper.FABRICA_BRANCHES_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fabricaResponse -> {
                    tradeInView.hideLoading();
                    tradeInView.showBranches(fabricaResponse.getData());
                }, throwable -> {
                    tradeInView.hideLoading();
                    if (throwable != null)
                        tradeInView.showMessage(throwable.getMessage());
                });
    }

    @Override
    public void sendRequest(String name, String email, String phone,
                            int myCarBrandId, int myCarModelId, int requestedCarBrandId, int requestedCarModelId, int branchId) {
        tradeInView.showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("phone_number", phone);
            jsonObject.put("branch_id", branchId);
            jsonObject.put("my_car_brand_id", myCarBrandId);
            jsonObject.put("my_car_model_id", myCarModelId);
            jsonObject.put("requested_car_brand_id", requestedCarBrandId);
            jsonObject.put("requested_car_model_id", requestedCarModelId);
        } catch (JSONException ignored) {
        }

        ApiHelper.requestTradeIn(PrefUtils.getAppLang(context), jsonObject, new ApiHelper.ApiCallBack() {
            @Override
            public void onSuccess() {
                tradeInView.hideProgressDialog();
                tradeInView.showConfirmationDialog();
            }

            @Override
            public void onFail(String message) {
                tradeInView.hideProgressDialog();
                tradeInView.showMessage(message);
            }
        });
    }
}
