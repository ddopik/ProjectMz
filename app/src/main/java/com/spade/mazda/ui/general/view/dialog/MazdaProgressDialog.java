package com.spade.mazda.ui.general.view.dialog;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class MazdaProgressDialog extends DialogFragment {

    private int loadingTextResID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mazda_progress_dialog, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        CustomTextView loadingText = view.findViewById(R.id.loading_text);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
        loadingText.setText(loadingTextResID);
    }


    public void setLoadingTextResID(int loadingTextResID) {
        this.loadingTextResID = loadingTextResID;
    }
}
