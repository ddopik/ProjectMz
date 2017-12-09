package com.spade.mazda.ui.authentication.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenter;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenterImpl;
import com.spade.mazda.ui.authentication.view.activity.ActivationActivity;
import com.spade.mazda.ui.authentication.view.adapter.NationalIDsAdapter;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.ui.general.view.CarColorsSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarModelsSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarTrimSpinnerAdapter;
import com.spade.mazda.ui.general.view.CarYearsSpinnerAdapter;
import com.spade.mazda.ui.general.view.PickImageDialogFragment;
import com.spade.mazda.utils.PrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class RegistrationSecondStepFragment extends BaseFragment implements RegistrationView,
        NationalIDsAdapter.NationalImageView, PickImageDialogFragment.PickImageActions {

    private static final int LIST_SIZE = 2;

    private RegistrationPresenter registrationPresenter;

    private View view;
    private RelativeLayout chooseImage;
    private AppCompatSpinner carModelSpinner, carYearsSpinner, carTrimsSpinner, carColorsSpinner;
    private CustomEditText chassisEditText, motorEditText, nationalIDEditText;
    private ProgressBar progressBar;

    private NationalIDsAdapter nationalIDsAdapter;
    private CarModelsSpinnerAdapter carModelsSpinnerAdapter;
    private CarYearsSpinnerAdapter carYearsSpinnerAdapter;
    private CarTrimSpinnerAdapter carTrimSpinnerAdapter;
    private CarColorsSpinnerAdapter carColorsSpinnerAdapter;

    private List<CarModel> carModelList = new ArrayList<>();
    private List<CarYear> carYears = new ArrayList<>();
    private List<ModelTrim> modelTrims = new ArrayList<>();
    private List<TrimColor> trimColors = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    private int modelId = -1, yearId = -1, trimId = -1, colorId = -1;
    private String chassisString, motorString, nationalIDString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registration_step_two, container, false);
        initViews();
        return view;
    }

    @Override
    protected void initPresenter() {
        registrationPresenter = RegistrationPresenterImpl.getInstance();
        registrationPresenter.setView(this);
    }

    @Override
    protected void initViews() {

        String appLang = PrefUtils.getAppLang(getContext());
        CustomRecyclerView customRecyclerView = view.findViewById(R.id.photos_recycler_view);
        CustomButton registerBtn = view.findViewById(R.id.register_btn);

        carModelSpinner = view.findViewById(R.id.car_model_spinner);
        carYearsSpinner = view.findViewById(R.id.car_year_spinner);
        carTrimsSpinner = view.findViewById(R.id.car_trim_spinner);
        carColorsSpinner = view.findViewById(R.id.car_color_spinner);

        chooseImage = view.findViewById(R.id.choose_image);
        chassisEditText = view.findViewById(R.id.chassis_edit_text);
        motorEditText = view.findViewById(R.id.motor_edit_text);
        nationalIDEditText = view.findViewById(R.id.id_number_edit_text);
        progressBar = view.findViewById(R.id.progress_bar);

        carModelsSpinnerAdapter = new CarModelsSpinnerAdapter(carModelList, getContext());
        carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
        carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());
        carColorsSpinnerAdapter = new CarColorsSpinnerAdapter(trimColors, getContext());

        nationalIDsAdapter = new NationalIDsAdapter(getContext(), files);
        nationalIDsAdapter.setNationalImageView(this);
        customRecyclerView.setAdapter(nationalIDsAdapter);

        carModelSpinner.setAdapter(carModelsSpinnerAdapter);
        carYearsSpinner.setAdapter(carYearsSpinnerAdapter);
        carTrimsSpinner.setAdapter(carTrimSpinnerAdapter);
        carColorsSpinner.setAdapter(carColorsSpinnerAdapter);

        setUpCarsSpinners();

        chassisEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registrationPresenter.setChassisString(editable.toString());
            }
        });

        motorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registrationPresenter.setMotorString(editable.toString());
            }
        });

        nationalIDEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registrationPresenter.setNationalIDString(editable.toString());
            }
        });

        chooseImage.setOnClickListener(view1 -> showImagePickerDialog());

        registerBtn.setOnClickListener(view1 -> {
            if (dataIsValid()) {
                File[] imageFiles = files.toArray(new File[files.size()]);
                registrationPresenter.register(appLang, chassisString, motorString, nationalIDString, modelId, yearId, trimId, colorId, imageFiles);
            }
        });

        registrationPresenter.getCarModels();
        setData();
    }


    private boolean dataIsValid() {
        chassisString = chassisEditText.getText().toString();
        motorString = motorEditText.getText().toString();
        nationalIDString = nationalIDEditText.getText().toString();

        if (modelId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_model), Toast.LENGTH_LONG).show();
            return false;
        }


        if (yearId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_year), Toast.LENGTH_LONG).show();
            return false;
        }

        if (trimId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_trim), Toast.LENGTH_LONG).show();
            return false;
        }

        if (colorId == -1) {
            Toast.makeText(getContext(), getString(R.string.please_choose_car_color), Toast.LENGTH_LONG).show();
            return false;
        }

        if (chassisString == null || chassisString.isEmpty()) {
            chassisEditText.setError(getString(R.string.enter_chassis));
            return false;
        }

        if (motorString == null || motorString.isEmpty()) {
            motorEditText.setError(getString(R.string.enter_motor));
            return false;
        }

        if (nationalIDString == null || nationalIDString.isEmpty()) {
            nationalIDEditText.setError(getString(R.string.national_id_number));
            return false;
        } else if (nationalIDString.length() != 14) {
            nationalIDEditText.setError(getString(R.string.national_id_constraint));
            return false;
        }

        if (files.size() < LIST_SIZE) {
            Toast.makeText(getContext(), getString(R.string.please_choose_national_id), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void showImagePickerDialog() {
        PickImageDialogFragment pickImageDialogFragment = new PickImageDialogFragment();
        pickImageDialogFragment.setPickImageActions(this);
        pickImageDialogFragment.show(getChildFragmentManager(), PickImageDialogFragment.class.getSimpleName());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }


    @Override
    public void showCarModels(List<CarModel> carModels) {
        this.carModelList.clear();
        this.carModelList.addAll(carModels);
        carModelsSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToNextStep() {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateToActivate() {
        startActivity(ActivationActivity.getLaunchIntent(getContext()));
        getActivity().finish();
    }

    @Override
    public void navigateToLogin() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                addUri(imageFile);
            }
        });
    }

    private void addUri(File file) {
        this.files.add(file);
        registrationPresenter.setFiles(this.files);
        notifyNationalIdsPhotos();
    }

    private void notifyNationalIdsPhotos() {
        if (files.size() < LIST_SIZE) {
            chooseImage.setVisibility(View.VISIBLE);
        } else {
            chooseImage.setVisibility(View.GONE);
        }
        this.nationalIDsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClearClicked(File file) {
        this.files.remove(file);
        notifyNationalIdsPhotos();
    }

    @Override
    public void onGalleryClicked() {
        registrationPresenter.pickImageFromGallery(this);
    }

    @Override
    public void onCameraClicked() {
        registrationPresenter.pickImageFromCamera(this);
    }

    private void setUpCarsSpinners() {
        carModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel carModel = carModelList.get(i);
                modelId = carModel.getCarModelId();
                carYears.clear();
                carYears.addAll(carModel.getCarYears());
                carYearsSpinnerAdapter = new CarYearsSpinnerAdapter(carYears, getContext());
                carYearsSpinner.setAdapter(carYearsSpinnerAdapter);
                registrationPresenter.setModelPosition(i);
                setUpYearsSpinner();
                carYearsSpinner.setSelection(registrationPresenter.getYearPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpYearsSpinner() {
        carYearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarYear carYear = carYears.get(i);
                yearId = carYear.getYearID();
                modelTrims.clear();
                modelTrims.addAll(carYear.getModelTrims());
                carTrimSpinnerAdapter = new CarTrimSpinnerAdapter(modelTrims, getContext());
                carTrimsSpinner.setAdapter(carTrimSpinnerAdapter);
                registrationPresenter.setYearPosition(i);
                setUpTrimsSpinner();
                carTrimsSpinner.setSelection(registrationPresenter.getTrimPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpTrimsSpinner() {
        carTrimsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ModelTrim modelTrim = modelTrims.get(i);
                trimId = modelTrim.getTrimId();
                trimColors.clear();
                trimColors.addAll(modelTrim.getTrimColors());
                carColorsSpinnerAdapter = new CarColorsSpinnerAdapter(trimColors, getContext());
                carColorsSpinner.setAdapter(carColorsSpinnerAdapter);
                registrationPresenter.setTrimPosition(i);
                setUpColorsSpinner();
                carColorsSpinner.setSelection(registrationPresenter.getColorPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpColorsSpinner() {
        carColorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TrimColor trimColor = trimColors.get(i);
                colorId = trimColor.getColorID();
                registrationPresenter.setColorPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setData() {
        chassisEditText.setText(registrationPresenter.getChassisString());
        motorEditText.setText(registrationPresenter.getMotorString());
        nationalIDEditText.setText(registrationPresenter.getNationalIDString());
        carModelSpinner.setSelection(registrationPresenter.getModelPosition());
        files.addAll(registrationPresenter.getFiles());
        nationalIDsAdapter.notifyDataSetChanged();
    }

}
