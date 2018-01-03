package com.spade.mazda.ui.profile.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.authentication.view.adapter.NationalIDsAdapter;
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.general.view.dialog.PickImageDialogFragment;
import com.spade.mazda.ui.profile.presenter.EditProfilePresenter;
import com.spade.mazda.ui.profile.presenter.EditProfilePresenterImpl;
import com.spade.mazda.ui.profile.view.activity.EditCarActivity;
import com.spade.mazda.ui.profile.view.interfaces.EditProfileView;
import com.spade.mazda.utils.GlideApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileView, PickDateDialog.OnDateSet,
        NationalIDsAdapter.NationalImageView, PickImageDialogFragment.PickImageActions, View.OnClickListener {

    private static final int LIST_SIZE = 2;
    private static final int EDIT_CAR_REQUEST_CODE = 201;
    private static final int USER_IMAGE = 3;
    private static final int NATIONAL_ID_IMAGE = 4;
    private int imageType;
    private View view;
    private RelativeLayout chooseImage;
    private CustomEditText nameEditText, emailEditText, mobileNumberEditText, birthDateEditText, nationalIDEditText;
    private CustomTextView carModelName, carChassis, carMotor, carYear, carColor;
    private ImageView userImage, carImage;

    private MazdaProgressDialog progressDialog;

    private NationalIDsAdapter nationalIDsAdapter;

//    private String nameString;
    //    private String emailString;
//    private String mobileNumberString;

    private List<File> files = new ArrayList<>();

    private EditProfilePresenter editProfilePresenter;
//    private String nationalIDString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initViews();
        return view;
    }

    @Override
    protected void initPresenter() {
        editProfilePresenter = new EditProfilePresenterImpl(getContext());
        editProfilePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomRecyclerView customRecyclerView = view.findViewById(R.id.photos_recycler_view);
        CustomTextView editCarDetails = view.findViewById(R.id.edit_car_details);
        CustomTextView editProfilePicture = view.findViewById(R.id.edit_profile_picture);
        CustomButton editButton = view.findViewById(R.id.edit_btn);
        LinearLayout carInfoLayout = view.findViewById(R.id.car_info_layout);

        chooseImage = view.findViewById(R.id.choose_image);
        nameEditText = view.findViewById(R.id.name_edit_text);
        emailEditText = view.findViewById(R.id.email_edit_text);
        mobileNumberEditText = view.findViewById(R.id.mobile_edit_text);
        birthDateEditText = view.findViewById(R.id.birth_date_edit_text);
        nationalIDEditText = view.findViewById(R.id.id_number_edit_text);
        carModelName = view.findViewById(R.id.model_title);
        carChassis = view.findViewById(R.id.chassis);
        carMotor = view.findViewById(R.id.motor);
        carYear = view.findViewById(R.id.model_year);
        carColor = view.findViewById(R.id.model_color);
        carImage = view.findViewById(R.id.model_image);
        userImage = view.findViewById(R.id.user_image);

        carInfoLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.blue_rounded_background));
        editCarDetails.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        editProfilePicture.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        nationalIDEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        birthDateEditText.setFocusable(false);
        birthDateEditText.setClickable(true);

        nationalIDsAdapter = new NationalIDsAdapter(getContext(), files);
        nationalIDsAdapter.setNationalImageView(this);
        customRecyclerView.setAdapter(nationalIDsAdapter);

        birthDateEditText.setOnClickListener(this);
        chooseImage.setOnClickListener(this);
        editProfilePicture.setOnClickListener(this);
        editCarDetails.setOnClickListener(this);
        editButton.setOnClickListener(this);

        editProfilePresenter.getUserData();
        editProfilePresenter.getCarData();
    }


    private void showDatePicker() {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(getChildFragmentManager(), PickDateDialog.class.getSimpleName());
    }


    @Override
    public void onDateSet(int year, int month, int day) {
        String birthDateString = day + "-" + (month + 1) + "-" + year;
        birthDateEditText.setText(birthDateString);
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
    public void setCarColor(String carColor) {
        this.carColor.setText(String.format(getString(R.string.color), carColor));
    }

    @Override
    public void setChassis(String chassis) {
        carChassis.setText(String.format(getString(R.string.chassis_number), chassis));
    }

    @Override
    public void setMotor(String motor) {
        carMotor.setText(String.format(getString(R.string.motor_number), motor));
    }

    @Override
    public void setNameError() {
        nameEditText.setError(getString(R.string.enter_user_name));
    }

    @Override
    public void setPhoneError() {
        mobileNumberEditText.setError(getString(R.string.enter_phone_number));

    }

    @Override
    public void setNationalIDError(int resID) {
        nationalIDEditText.setError(getString(resID));

    }

    @Override
    public void setBirthDateError() {
        birthDateEditText.setError(getString(R.string.pick_birth_date));
    }

    @Override
    public void setUserName(String name) {
        nameEditText.setText(name);

    }

    @Override
    public void setUserEmail(String email) {
        emailEditText.setText(email);

    }

    @Override
    public void setUserPhone(String phone) {
        mobileNumberEditText.setText(phone);

    }

    @Override
    public void setUserBirthDate(String birthDate) {
        birthDateEditText.setText(birthDate);
    }

    @Override
    public void setCarImage(String carImage) {
        GlideApp.with(getContext()).load(carImage).into(this.carImage);
    }

    @Override
    public void setNationalID(String nationalID) {
        nationalIDEditText.setText(nationalID);
    }

    @Override
    public void setCarModel(String carModel) {
        carModelName.setText(carModel);
    }

    @Override
    public void setCarYear(String carYear) {
        this.carYear.setText(String.format(getString(R.string.model), carYear));
    }

    @Override
    public void setCarTrim(String carTrim) {

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
    public void finish() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setError(EditText editText, int resId) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CAR_REQUEST_CODE) {
            editProfilePresenter.getCarData();
        } else {
            EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                }

                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    if (imageType == USER_IMAGE) {
                        setUserImage(imageFile);
                    } else {
                        addUri(imageFile);
                    }
                }
            });
        }
    }

    private void setUserImage(File userImage) {
        Uri uri = Uri.fromFile(userImage);
        GlideApp.with(this).load(uri).
                apply(RequestOptions.circleCropTransform()).
                placeholder(R.drawable.ic_profile_default).into(this.userImage);
        editProfilePresenter.setUserImage(userImage);
    }

    private void addUri(File file) {
        this.files.add(file);
        editProfilePresenter.setFiles(files);
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
        editProfilePresenter.pickImageFromGallery(this);
    }

    @Override
    public void onCameraClicked() {
        editProfilePresenter.pickImageFromCamera(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn:
                editProfilePresenter.setName(nameEditText.getText().toString());
                editProfilePresenter.setMobileNumber(mobileNumberEditText.getText().toString());
                editProfilePresenter.setNationalId(nationalIDEditText.getText().toString());
                editProfilePresenter.setBirthDate(birthDateEditText.getText().toString());
                if (editProfilePresenter.isDataValid()) {
                    editProfilePresenter.editProfile();
                }
                break;
            case R.id.birth_date_edit_text:
                showDatePicker();
                break;
            case R.id.choose_image:
                imageType = NATIONAL_ID_IMAGE;
                showImagePickerDialog();
                break;
            case R.id.edit_car_details:
                startActivityForResult(EditCarActivity.getLaunchIntent(getContext()), EDIT_CAR_REQUEST_CODE);
                break;
            case R.id.edit_profile_picture:
                imageType = USER_IMAGE;
                showImagePickerDialog();
                break;
        }
    }


}
