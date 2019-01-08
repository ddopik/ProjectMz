package com.spade.mazda.ui.general.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Window;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class MazdaDialogFragment extends DialogFragment {

    public static final String EXTRA_CAR_MODELS = "EXTRA_CAR_MODELS";
    public static final String EXTRA_CAR_YEARS = "EXTRA_CAR_YEARS";
    public static final String EXTRA_CAR_TRIMS = "EXTRA_CAR_TRIMS";
    public static final String EXTRA_CAR_COLORS = "EXTRA_CAR_COLORS";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
