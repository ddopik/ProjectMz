package com.spade.mazda.ui.general.view;

import android.content.DialogInterface;
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

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class ConfirmationDialogFragment extends DialogFragment {

    private String confirmationMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirmed, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        init(view);
        return view;
    }

    private void init(View view) {
        CustomTextView confirmationMessageTextView = view.findViewById(R.id.dialog_message);
        confirmationMessageTextView.setText(confirmationMessage);
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }
}
