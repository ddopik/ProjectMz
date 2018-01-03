package com.spade.mazda.ui.cars.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.presenter.interfaces.DriveFinancePresenter;
import com.spade.mazda.ui.cars.view.interfaces.DriveFinanceView;
import com.spade.mazda.ui.general.view.dialog.InstallmentYearsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.ModelsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.ProgramsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.TrimsDialogFragment;
import com.spade.mazda.ui.general.view.dialog.UserDataDialogFragment;
import com.spade.mazda.ui.general.view.dialog.YearsDialogFragment;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;
import com.spade.mazda.ui.general.view.interfaces.CarTrimInterface;
import com.spade.mazda.ui.general.view.interfaces.CarYearInterface;
import com.spade.mazda.ui.general.view.interfaces.InterestRatesInterface;
import com.spade.mazda.ui.general.view.interfaces.ProgramsInterface;
import com.spade.mazda.ui.services.model.InterestRates;
import com.spade.mazda.ui.services.model.Program;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public class DriveFinancePresenterImpl implements DriveFinancePresenter,
        CarModelInterface,
        CarYearInterface,
        CarTrimInterface,
        ProgramsInterface,
        InterestRatesInterface,
        UserDataDialogFragment.OnDoneClicked {

    private static final double DOWN_PAYMENT_PERCENTAGE = 0.2;
    private static final int MONTHS_PER_YEAR = 12;
    private static final int FIRST_ITEM = 0;

    private DriveFinanceView driveFinanceView;
    private Context context;
    private RealmDbHelper realmDbHelper;
    private FragmentManager fragmentManager;

    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();
    private List<Program> programs = new ArrayList<>();
    private List<InterestRates> interestRatesList = new ArrayList<>();

    private CarModel carModel;
    private CarYear carYear;
    private ModelTrim modelTrim;
    private int modelId = -1, yearId = -1, trimId = -1, programId = -1, numberOfYears = 0;
    private double interestRate = -1, carPrice, downPaymentValue;

    public DriveFinancePresenterImpl(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        realmDbHelper = new RealmDbImpl();
    }

    @Override
    public void setView(DriveFinanceView view) {
        driveFinanceView = view;
    }

    @Override
    public void getPrograms() {
        driveFinanceView.showLoading();
        ApiHelper.getPrograms(PrefUtils.getAppLang(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programsResponse -> {
                    driveFinanceView.hideLoading();
                    programs.addAll(programsResponse.getData());
                }, throwable -> {
                    driveFinanceView.hideLoading();
                });
    }


    @Override
    public boolean isDownPaymentValid(String downPayment) {
        try {
            downPaymentValue = Double.parseDouble(downPayment);
            if (downPaymentValue < (carPrice * DOWN_PAYMENT_PERCENTAGE)) {
                driveFinanceView.setDownPaymentError(R.string.down_payment_message_1);
                return false;
            } else if (downPaymentValue >= carPrice) {
                driveFinanceView.setDownPaymentError(R.string.down_payment_message_2);
                return false;
            }
        } catch (NumberFormatException e) {
            driveFinanceView.setDownPaymentError(R.string.enter_valid_amount);
            return false;
        }
        return true;
    }

    @Override
    public void calculateMonthlyInstallment(double carPrice, String downPayment, int numberOfYears) {
        double downPaymentValue = Double.parseDouble(downPayment);
        double difference = carPrice - downPaymentValue;
        double loanAmount = difference + (difference * 0.1);
        int numberOfMonths = numberOfYears * MONTHS_PER_YEAR;
        int monthlyInstallmentValue = (int) (loanAmount / numberOfMonths);
        String monthlyInstallment = String.format(context.getString(R.string.currency), String.valueOf(monthlyInstallmentValue));
        driveFinanceView.showMonthlyInstallment(monthlyInstallment);
    }

    @Override
    public void calculateMonthlyInstallment() {
        double difference = carPrice - downPaymentValue;
        double loanAmount = difference + (difference * interestRate);
        int numberOfMonths = numberOfYears * MONTHS_PER_YEAR;
        int monthlyInstallmentValue = (int) (loanAmount / numberOfMonths);
        String monthlyInstallment = String.format(context.getString(R.string.currency), String.valueOf(monthlyInstallmentValue));
        driveFinanceView.showMonthlyInstallment(monthlyInstallment);
    }

    @Override
    public void showCarModelsDialog() {
        ModelsDialogFragment modelsDialogFragment = new ModelsDialogFragment();
        modelsDialogFragment.setModelActions(this);
        modelsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
    }

    @Override
    public void showCarYearsDialog() {
        if (carYears.isEmpty()) {
            driveFinanceView.showMessage(R.string.please_choose_car_model);
        } else {
            YearsDialogFragment yearsDialogFragment = new YearsDialogFragment();
            yearsDialogFragment.setCarYears(carYears);
            yearsDialogFragment.setCarYearInterface(this);
            yearsDialogFragment.show(fragmentManager, YearsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showCarTrimsDialog() {
        if (modelTrims.isEmpty()) {
            driveFinanceView.showMessage(R.string.please_choose_car_year);
        } else {
            TrimsDialogFragment trimsDialogFragment = new TrimsDialogFragment();
            trimsDialogFragment.setModelTrimInterface(this);
            trimsDialogFragment.setModelTrims(modelTrims);
            trimsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showProgramsDialog() {
        if (programs.isEmpty()) {
            driveFinanceView.showMessage(R.string.please_choose_car_year);
        } else {
            ProgramsDialogFragment programsDialogFragment = new ProgramsDialogFragment();
            programsDialogFragment.setProgramsInterface(this);
            programsDialogFragment.setPrograms(programs);
            programsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void showYearsDialog() {
        if (interestRatesList.isEmpty()) {
            driveFinanceView.showMessage(R.string.please_choose_car_year);
        } else {
            InstallmentYearsDialogFragment yearsDialogFragment = new InstallmentYearsDialogFragment();
            yearsDialogFragment.setInterestRatesInterface(this);
            yearsDialogFragment.setInterestRatesList(interestRatesList);
            yearsDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public boolean dataIsValid(String downPayment) {
        if (modelId == -1) {
            driveFinanceView.setCarModelError();
            return false;
        }

        if (yearId == -1) {
            driveFinanceView.setCarYearError();
            return false;
        }

        if (trimId == -1) {
            driveFinanceView.setCarTrimError();
            return false;
        }

        if (programId == -1) {
            driveFinanceView.setProgramError();
            return false;
        }

        if (numberOfYears == 0) {
            driveFinanceView.setNumberOfYearsError();
            return false;
        }

        if (!isDownPaymentValid(downPayment)) {
            return false;
        }


        return true;
    }

    @Override
    public void onYearSelected(CarYear carYear) {
        this.carYear = carYear;
        yearId = carYear.getYearID();
        modelTrims.clear();
        modelTrims.addAll(carYear.getModelTrims());
        driveFinanceView.setCarYear(carYear.getYearName());
        if (modelTrims != null && !modelTrims.isEmpty()) {
            onTrimSelected(modelTrims.get(FIRST_ITEM));
        }
    }

    @Override
    public void onModelSelected(CarModel carModel) {
        this.carModel = carModel;
        modelId = carModel.getCarModelId();
        carYears.clear();
        carYears.addAll(carModel.getCarYears());
        driveFinanceView.setCarModel(carModel.getCarModelName());
        if (carYears != null && !carYears.isEmpty()) {
            onYearSelected(carYears.get(FIRST_ITEM));
        }
    }

    @Override
    public void onTrimSelected(ModelTrim modelTrim) {
        this.modelTrim = modelTrim;
        trimId = modelTrim.getTrimId();
        carPrice = Double.valueOf(modelTrim.getTrimPrice());
        driveFinanceView.setCarTrim(modelTrim.getTrimName());
        driveFinanceView.setOriginalPrice(modelTrim.getTrimPrice());
    }

//    @Override
//    public void getUser() {
//        user = realmDbHelper.getUser(PrefUtils.getUserId(context));
//        driveFinanceView.setChassis(user.getChassis());
//        driveFinanceView.setMotor(user.getMotor());
//        getCarDetails(user.getCarModel());
//    }

    public CarModel getCarModel() {
        return carModel;
    }

    public CarYear getCarYear() {
        return carYear;
    }

    public ModelTrim getModelTrim() {
        return modelTrim;
    }


    public void setCarModel(CarModel carModel) {
        onModelSelected(carModel);
    }

    public void setCarYear(CarYear carYear) {
        onYearSelected(carYear);
    }

    public void setModelTrim(ModelTrim modelTrim) {
        onTrimSelected(modelTrim);
    }

    @Override
    public void onProgramSelected(Program program) {
        this.interestRatesList.clear();
        this.interestRatesList.addAll(program.getInterestRatesList());
        driveFinanceView.setSelectedProgram(program.getTitle());
        programId = program.getId();
    }

    @Override
    public void onRateSelected(InterestRates interestRate) {
        if (interestRate.getInterestRate() > 0) {
            this.interestRate = interestRate.getInterestRate() / 100;
        } else {
            this.interestRate = interestRate.getInterestRate();
        }
        driveFinanceView.setNumberOfYears(String.valueOf(interestRate.getYearNumber()));
        numberOfYears = interestRate.getYearNumber();
    }


    @Override
    public void getCarDetails(int carID) {
        DataSource dataSource = DataSource.getInstance();
        dataSource.getCarModel(carID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onModelSelected);
    }

    @Override
    public void showUserDataFragment() {
        UserDataDialogFragment userDataDialogFragment = new UserDataDialogFragment();
        userDataDialogFragment.setOnDoneClicked(this);
        userDataDialogFragment.show(fragmentManager, ModelsDialogFragment.class.getSimpleName());
    }

    @Override
    public void submitAsInterested() {
        if (PrefUtils.isLoggedIn(context)) {
            User user = realmDbHelper.getUser(PrefUtils.getUserId(context));
            submit(user.getUserName(), user.getUserPhone());
        } else {
            showUserDataFragment();
        }
    }

    @Override
    public void onDoneClicked(String name, String phone) {
        submit(name, phone);
    }

    private void submit(String name, String phone) {
        driveFinanceView.showProgressDialog();
        ApiHelper.interested(PrefUtils.getAppLang(context), name, phone,
                String.valueOf(trimId), String.valueOf(programId),
                String.valueOf(numberOfYears), String.valueOf(downPaymentValue), new ApiHelper.ApiCallBack() {
                    @Override
                    public void onSuccess() {
                        driveFinanceView.hideProgressDialog();
                        driveFinanceView.showSuccessDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        driveFinanceView.hideProgressDialog();
                        driveFinanceView.showMessage(message);
                    }
                });
    }

}
