package com.spade.mazda.ui.authentication.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenter;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenterImpl;
import com.spade.mazda.ui.authentication.view.activity.ActivationActivity;
import com.spade.mazda.ui.authentication.view.adapter.NationalIDsAdapter;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.general.view.dialog.PickImageDialogFragment;
import com.spade.mazda.utils.PrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class RegistrationFirstStepFragment extends BaseFragment
        implements RegistrationView,
        NationalIDsAdapter.NationalImageView,
        PickImageDialogFragment.PickImageActions,
        View.OnClickListener {

    public static final int LIST_SIZE = 2;
    private RegistrationPresenter registrationPresenter;

    private View view;
    private RelativeLayout chooseImage;
    private CustomEditText chassisEditText, motorEditText, nationalIDEditText,
            carModelEditText, carYearsEditText, carTrimsEditText, carColorsEditText;
    private MazdaProgressDialog progressDialog;
    private OnNextClicked onNextClicked;
    private NationalIDsAdapter nationalIDsAdapter;

    private List<File> files = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registration_step_one, container, false);
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
        CustomRecyclerView customRecyclerView = view.findViewById(R.id.photos_recycler_view);
        CustomButton nextButton = view.findViewById(R.id.next_btn);

        chooseImage = view.findViewById(R.id.choose_image);
        chassisEditText = view.findViewById(R.id.chassis_edit_text);
        motorEditText = view.findViewById(R.id.motor_edit_text);
        nationalIDEditText = view.findViewById(R.id.id_number_edit_text);
        carModelEditText = view.findViewById(R.id.car_model_edit_text);
        carYearsEditText = view.findViewById(R.id.car_year_edit_text);
        carTrimsEditText = view.findViewById(R.id.car_trim_edit_text);
        carColorsEditText = view.findViewById(R.id.car_color_edit_text);

        nationalIDsAdapter = new NationalIDsAdapter(getContext(), files);
        nationalIDsAdapter.setNationalImageView(this);
        customRecyclerView.setAdapter(nationalIDsAdapter);

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

        chooseImage.setOnClickListener(this);
        carModelEditText.setOnClickListener(this);
        carYearsEditText.setOnClickListener(this);
        carTrimsEditText.setOnClickListener(this);
        carColorsEditText.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        setData();
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
        Toast.makeText(getActivity(), getString(resID), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void navigateToNextStep() {

    }

    @Override
    public void showLoading() {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(R.string.loading);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void navigateToActivate(String email) {
        DataSource dataSource = DataSource.getInstance();
        dataSource.setActivationSource(DataSource.REGISTER_ACTIVATION);
        Intent intent = ActivationActivity.getLaunchIntent(getContext());
        intent.putExtra(ActivationActivity.EXTRA_EMAIL, email);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void navigateToLogin() {

    }

    @Override
    public void setCarModel(String carModel) {
        carModelEditText.setText(carModel);
    }

    @Override
    public void setCarYear(String carYear) {
        carYearsEditText.setText(carYear);
    }

    @Override
    public void setCarTrim(String carTrim) {
        carTrimsEditText.setText(carTrim);
    }

    @Override
    public void setCarColor(String carColor) {
        carColorsEditText.setText(carColor);
    }

    @Override
    public void setCarModelError() {
        carModelEditText.setError(getString(R.string.please_choose_car_model));
    }

    @Override
    public void setCarYearError() {
        carYearsEditText.setError(getString(R.string.please_choose_car_year));

    }

    @Override
    public void setCarTrimError() {
        carTrimsEditText.setError(getString(R.string.please_choose_car_trim));

    }

    @Override
    public void setCarColorError() {
        carColorsEditText.setError(getString(R.string.please_choose_car_color));

    }

    @Override
    public void setNationalIdError(int resID) {
        nationalIDEditText.setError(getString(resID));

    }

    @Override
    public void setChassisError() {
        chassisEditText.setError(getString(R.string.enter_chassis));
    }

    @Override
    public void setMotorError() {
        motorEditText.setError(getString(R.string.enter_motor));
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

    private void setData() {
        chassisEditText.setText(registrationPresenter.getChassisString());
        motorEditText.setText(registrationPresenter.getMotorString());
        nationalIDEditText.setText(registrationPresenter.getNationalIDString());
        files.addAll(registrationPresenter.getFiles());
        nationalIDsAdapter.notifyDataSetChanged();

        if (registrationPresenter.getCarModel() != null) {
            registrationPresenter.setCarModel(registrationPresenter.getCarModel());
        }
        if (registrationPresenter.getCarYear() != null) {
            registrationPresenter.setCarYear(registrationPresenter.getCarYear());
        }
        if (registrationPresenter.getModelTrim() != null) {
            registrationPresenter.setModelTrim(registrationPresenter.getModelTrim());
        }
        if (registrationPresenter.getTrimColor() != null) {
            registrationPresenter.setTrimColor(registrationPresenter.getTrimColor());
        }
    }

    @Override
    public void onClick(View view) {
        String appLang = PrefUtils.getAppLang(getContext());
        switch (view.getId()) {
            case R.id.choose_image:
                showImagePickerDialog();
                break;
            case R.id.next_btn:
                if (registrationPresenter.dataIsValid()) {
//                    registrationPresenter.saveFirstStepData(nameString, emailString, passwordString, mobileNumberString, birthDateString);
                    registrationPresenter.saveFirstStepData(chassisEditText.getText().toString(), motorEditText.getText().toString(), nationalIDEditText.getText().toString(), carModelEditText.getText().toString(), carYearsEditText.getText().toString(), carTrimsEditText.getText().toString(), carColorsEditText.getText().toString());
                    registrationPresenter.validateMazdaUserLogin(appLang, onNextClicked);
//                    onNextClicked.onNextClicked();
                }
                break;
            case R.id.car_model_edit_text:
                registrationPresenter.showCarModelsDialog(getChildFragmentManager());
                break;
            case R.id.car_year_edit_text:
                registrationPresenter.showCarYearsDialog(getChildFragmentManager());
                break;
            case R.id.car_trim_edit_text:
                registrationPresenter.showCarTrimsDialog(getChildFragmentManager());
                break;
            case R.id.car_color_edit_text:
                registrationPresenter.showCarColorsDialog(getChildFragmentManager());
                break;
        }

    }

    public void setOnNextClicked(OnNextClicked onNextClicked) {
        this.onNextClicked = onNextClicked;
    }

    public interface OnNextClicked {
        void onNextClicked();
    }
}
