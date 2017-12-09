package com.spade.mazda.ui.authentication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenter;
import com.spade.mazda.ui.authentication.presenter.RegistrationPresenterImpl;
import com.spade.mazda.ui.authentication.view.activity.ServerLoginActivity;
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.authentication.view.interfaces.RegistrationView;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.utils.Validator;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class RegistrationFirstStepFragment extends BaseFragment implements RegistrationView, PickDateDialog.OnDateSet {
    private CustomEditText nameEditText, emailEditText, passwordEditText,
            confirmPasswordEditText, mobileNumberEditText, birthDateEditText;
    private String nameString;
    private String emailString;
    private String passwordString;
    private String mobileNumberString;
    private String birthDateString;

    private View view;
    private OnNextClicked onNextClicked;

    private RegistrationPresenter registrationPresenter;

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
//        registrationPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        nameEditText = view.findViewById(R.id.name_edit_text);
        emailEditText = view.findViewById(R.id.email_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        confirmPasswordEditText = view.findViewById(R.id.confirm_password_edit_text);
        mobileNumberEditText = view.findViewById(R.id.mobile_edit_text);
        birthDateEditText = view.findViewById(R.id.birth_date_edit_text);

        birthDateEditText.setFocusable(false);
        birthDateEditText.setClickable(true);

        CustomButton nextButton = view.findViewById(R.id.next_btn);
        TextView signInText = view.findViewById(R.id.sign_in);
        birthDateEditText.setOnClickListener(view1 -> showDatePicker());
        signInText.setOnClickListener(view1 -> navigateToLogin());
        nextButton.setOnClickListener(btn -> {
            if (dataIsValid()) {
                registrationPresenter.saveFirstStepData(nameString, emailString, passwordString, mobileNumberString, birthDateString);
                onNextClicked.onNextClicked();
            }
        });

    }


    private boolean dataIsValid() {
        String confirmPasswordString = confirmPasswordEditText.getText().toString();
        nameString = nameEditText.getText().toString();
        emailString = emailEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        mobileNumberString = mobileNumberEditText.getText().toString();
        birthDateString = birthDateEditText.getText().toString();

        if (nameString == null || nameString.isEmpty()) {
            nameEditText.setError(getString(R.string.enter_user_name));
            return false;
        }

        if (emailString == null || emailString.isEmpty()) {
            emailEditText.setError(getString(R.string.enter_email_address));
            return false;
        } else if (!Validator.isEmailAddressValid(emailString)) {
            emailEditText.setError(getString(R.string.enter_valid_email_address));
            return false;
        }

        if (passwordString == null || passwordString.isEmpty()) {
            passwordEditText.setError(getString(R.string.enter_password));
            return false;
        } else if (passwordString.length() < 6) {
            passwordEditText.setError(getString(R.string.password_limit));
            return false;
        }
        if (confirmPasswordString.isEmpty()) {
            nameEditText.setError(getString(R.string.enter_confirm_password));
            return false;
        }
        if (!confirmPasswordString.equals(passwordString)) {
            confirmPasswordEditText.setError(getString(R.string.password_mismatch));
            return false;
        }
        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            mobileNumberEditText.setError(getString(R.string.enter_phone_number));
            return false;
        }
        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            mobileNumberEditText.setError(getString(R.string.enter_phone_number));
            return false;
        }

        if (birthDateString == null || birthDateString.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.pick_birth_date), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void showDatePicker() {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(getChildFragmentManager(), PickDateDialog.class.getSimpleName());
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void navigateToNextStep() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void navigateToActivate() {

    }

    @Override
    public void navigateToLogin() {
        getActivity().finish();
        startActivity(ServerLoginActivity.getLaunchIntent(getContext()));
    }

    public void setOnNextClicked(OnNextClicked onNextClicked) {
        this.onNextClicked = onNextClicked;
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        birthDateString = year + "-" + (month + 1) + "-" + day;
        birthDateEditText.setText(birthDateString);
    }

    @Override
    public void showCarModels(List<CarModel> carModels) {

    }

    public interface OnNextClicked {
        void onNextClicked();
    }

}
