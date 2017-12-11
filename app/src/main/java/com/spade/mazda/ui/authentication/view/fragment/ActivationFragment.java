package com.spade.mazda.ui.authentication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.activity.ActivationActivity;
import com.spade.mazda.ui.authentication.view.activity.ServerLoginActivity;
import com.spade.mazda.utils.PrefUtils;
import com.spade.mazda.utils.Validator;

/**
 * Created by Ayman Abouzeid on 11/13/17.
 */

public class ActivationFragment extends BaseFragment {
    private CustomEditText codeEditText, emailEditText;
    private String codeString, emailAddress;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activation, container, false);
        initViews();
        return view;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initViews() {
        codeEditText = view.findViewById(R.id.code_edit_text);
        emailEditText = view.findViewById(R.id.email_edit_text);
        CustomButton nextButton = view.findViewById(R.id.activate_btn);
        if (getArguments() != null && getArguments().getString(ActivationActivity.EXTRA_EMAIL) != null) {
            emailAddress = getArguments().getString(ActivationActivity.EXTRA_EMAIL);
            emailEditText.setText(emailAddress);
        }
        nextButton.setOnClickListener(btn -> {
            if (dataIsValid()) {
                activate(emailAddress);
            }
        });

    }


    private boolean dataIsValid() {
        codeString = codeEditText.getText().toString();
        emailAddress = emailEditText.getText().toString();

        if (emailAddress.isEmpty()) {
            emailEditText.setError(getString(R.string.enter_email_address));
            return false;
        } else if (!Validator.isEmailAddressValid(emailAddress)) {
            emailEditText.setError(getString(R.string.enter_valid_email_address));
            return false;
        }
        if (codeString.isEmpty()) {
            codeEditText.setError(getString(R.string.enter_activation_code));
            return false;
        }

        return true;
    }

    private void activate(String email) {
        ApiHelper.activateUser(PrefUtils.getAppLang(getContext()), email, codeString, new ApiHelper.ActivationActions() {
            @Override
            public void onActivationSuccess() {
                getActivity().finish();
                startActivity(ServerLoginActivity.getLaunchIntent(getContext()));
            }

            @Override
            public void onActivationFail(String message) {
                showErrorMessage(message);
            }
        });
    }

    private void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
