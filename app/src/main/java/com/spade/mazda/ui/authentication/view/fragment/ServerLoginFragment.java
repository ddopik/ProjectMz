package com.spade.mazda.ui.authentication.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.authentication.presenter.LoginPresenter;
import com.spade.mazda.ui.authentication.presenter.LoginPresenterImpl;
import com.spade.mazda.ui.authentication.view.activity.ActivationActivity;
import com.spade.mazda.ui.authentication.view.interfaces.LoginView;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.main.MainActivity;
import com.spade.mazda.utils.PrefUtils;
import com.spade.mazda.utils.Validator;


/**
 * Created by Ayman Abouzeid on 6/23/17.
 */

public class ServerLoginFragment extends BaseFragment implements LoginView {

    private EditText emailAddressEditText, passwordEditText;
    private String emailAddressString, passwordString;
    private MazdaProgressDialog progressDialog;

    private View fragmentView;
    private LoginPresenter loginPresenter;
    private DataSource dataSource = DataSource.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_server_login, container, false);
        initViews();
        return fragmentView;
    }

    @Override
    protected void initPresenter() {
        loginPresenter = new LoginPresenterImpl(this, getContext());
        loginPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        emailAddressEditText = fragmentView.findViewById(R.id.email_address_edit_text);
        passwordEditText = fragmentView.findViewById(R.id.password_edit_text);
        CustomButton proceedBtn = fragmentView.findViewById(R.id.next_btn);
        TextView forgetPassword = fragmentView.findViewById(R.id.forget_password);
        TextView joinNow = fragmentView.findViewById(R.id.join_now);
        joinNow.setOnClickListener(view -> {
        });
        proceedBtn.setOnClickListener(v -> {
            if (checkIfDataIsValid()) {
                proceed();
            }
        });
    }

    private boolean checkIfDataIsValid() {
        emailAddressString = emailAddressEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();

        if (emailAddressString.isEmpty()) {
            emailAddressEditText.setError(getString(R.string.enter_email_address));
            return false;
        }

        if (!Validator.isEmailAddressValid(emailAddressString)) {
            emailAddressEditText.setError(getString(R.string.enter_valid_email_address));
            return false;
        }

        if (passwordString.isEmpty()) {
            passwordEditText.setError(getString(R.string.enter_password));
            return false;
        } else if (passwordString.length() < 6) {
            passwordEditText.setError(getString(R.string.password_limit));
            return false;
        }
        return true;
    }

    private void proceed() {
        String appLang = PrefUtils.getAppLang(getContext());
        loginPresenter.serverLogin(appLang, emailAddressString, passwordString);
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
        getActivity().finish();
    }

    @Override
    public void navigate() {
        if (dataSource.getLoginSource() == DataSource.DIALOG_LOGIN) {
            finish();
        } else {
            finish();
            navigateToMainScreen();
        }
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(MainActivity.getLaunchIntent(getContext()));
    }

    @Override
    public void navigateToActivate() {
        dataSource.setActivationSource(DataSource.LOGIN_ACTIVATION);
        Intent intent = ActivationActivity.getLaunchIntent(getContext());
        intent.putExtra(ActivationActivity.EXTRA_EMAIL, emailAddressString);
        startActivity(intent);
        getActivity().finish();
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
    public void setError(EditText editText, int resId) {

    }
}
