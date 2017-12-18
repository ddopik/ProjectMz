package com.spade.mazda.ui.general.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.authentication.view.activity.RegistrationActivity;
import com.spade.mazda.ui.authentication.view.activity.ServerLoginActivity;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class LoginDialogFragment extends DialogFragment {

    private DataSource dataSource = DataSource.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init(view);
        return view;
    }

    private void init(View view) {
        CustomTextView loginText = view.findViewById(R.id.login_text);
        CustomTextView registerText = view.findViewById(R.id.register_text);
        loginText.setOnClickListener(btn -> {
            dataSource.setLoginSource(DataSource.DIALOG_LOGIN);
            dismiss();
            startActivity(ServerLoginActivity.getLaunchIntent(getContext()));
        });
        registerText.setOnClickListener(btn -> {
            dataSource.setRegisterSource(DataSource.DIALOG_REGISTER);
            dismiss();
            startActivity(RegistrationActivity.getLaunchIntent(getContext()));
        });

    }

}
