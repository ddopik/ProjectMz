package com.spade.mazda.ui.general.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class UserDataDialogFragment extends DialogFragment {
    private CustomEditText nameEditText, mobileNumberEditText;
    private String mobileNumberString, nameString;
    private OnDoneClicked onDoneClicked;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_user_data, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        nameEditText = view.findViewById(R.id.name_edit_text);
        mobileNumberEditText = view.findViewById(R.id.mobile_edit_text);
        CustomButton doneButton = view.findViewById(R.id.done_btn);
        doneButton.setOnClickListener(view1 -> {
            if (dataIsValid()) {
                onDoneClicked.onDoneClicked(nameString, mobileNumberString);
            }
        });
    }

    private boolean dataIsValid() {
        nameString = nameEditText.getText().toString();
        mobileNumberString = mobileNumberEditText.getText().toString();

        if (nameString == null || nameString.isEmpty()) {
            nameEditText.setError(getString(R.string.enter_user_name));
            return false;
        }

        if (mobileNumberString == null || mobileNumberString.isEmpty()) {
            mobileNumberEditText.setError(getString(R.string.enter_phone_number));
            return false;
        }

        return true;
    }

    public void setOnDoneClicked(OnDoneClicked onDoneClicked) {
        this.onDoneClicked = onDoneClicked;
    }

    public interface OnDoneClicked {
        void onDoneClicked(String name, String phone);
    }
}

