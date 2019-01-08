package com.spade.mazda.ui.profile.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.profile.presenter.EditCarDetailsPresenterImpl;
import com.spade.mazda.ui.profile.presenter.EditCarPresenter;
import com.spade.mazda.ui.profile.view.interfaces.EditCarView;

/**
 * Created by Ayman Abouzeid on 12/25/17.
 */

public class EditCarFragment extends BaseFragment implements EditCarView, View.OnClickListener {

    private EditCarPresenter editCarPresenter;
    private View view;
    private CustomEditText chassisEditText, motorEditText,
            carModelEditText, carYearsEditText, carTrimsEditText, carColorsEditText;
    private MazdaProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_car_details, container, false);
        initViews();
        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int resID) {
        Toast.makeText(getContext(), getString(resID), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(R.string.loading);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }


    @Override
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showConfirmationDialog() {

    }

    @Override
    public void finish() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
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
    public void setChassisError() {
        chassisEditText.setError(getString(R.string.enter_chassis));
    }

    @Override
    public void setMotorError() {
        motorEditText.setError(getString(R.string.enter_motor));
    }

    @Override
    public void setChassis(String chassis) {
        chassisEditText.setText(chassis);
    }

    @Override
    public void setMotor(String motor) {
        motorEditText.setText(motor);
    }

    @Override
    protected void initPresenter() {
        editCarPresenter = new EditCarDetailsPresenterImpl(getContext());
        editCarPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomButton editBtn = view.findViewById(R.id.edit_btn);

        chassisEditText = view.findViewById(R.id.chassis_edit_text);
        motorEditText = view.findViewById(R.id.motor_edit_text);
        carModelEditText = view.findViewById(R.id.car_model_edit_text);
        carYearsEditText = view.findViewById(R.id.car_year_edit_text);
        carTrimsEditText = view.findViewById(R.id.car_trim_edit_text);
        carColorsEditText = view.findViewById(R.id.car_color_edit_text);

        chassisEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editCarPresenter.setChassisString(editable.toString());
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
                editCarPresenter.setMotorString(editable.toString());
            }
        });

        carModelEditText.setOnClickListener(this);
        carYearsEditText.setOnClickListener(this);
        carTrimsEditText.setOnClickListener(this);
        carColorsEditText.setOnClickListener(this);
        editBtn.setOnClickListener(this);

        editCarPresenter.getUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn:
                if (editCarPresenter.dataIsValid()) {
                    editCarPresenter.edit();
                }
                break;
            case R.id.car_model_edit_text:
                editCarPresenter.showCarModelsDialog(getChildFragmentManager());
                break;
            case R.id.car_year_edit_text:
                editCarPresenter.showCarYearsDialog(getChildFragmentManager());
                break;
            case R.id.car_trim_edit_text:
                editCarPresenter.showCarTrimsDialog(getChildFragmentManager());
                break;
            case R.id.car_color_edit_text:
                editCarPresenter.showCarColorsDialog(getChildFragmentManager());
                break;
        }
    }
}
