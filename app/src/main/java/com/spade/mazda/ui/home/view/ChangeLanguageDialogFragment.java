package com.spade.mazda.ui.home.view;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.main.MainActivity;
import com.spade.mazda.utils.PrefUtils;

public class ChangeLanguageDialogFragment extends DialogFragment {


    View mainVew;
    TextView englishBtn, arabicBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mainVew = inflater.inflate(R.layout.change_language_dialog, container, false);


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        inti();
        return mainVew;
    }

    private void inti() {

        englishBtn = mainVew.findViewById(R.id.english_btn);
        arabicBtn = mainVew.findViewById(R.id.arabic_btn);


        if (PrefUtils.getAppLang(getActivity()).equals(PrefUtils.ENGLISH_LANG))
            englishBtn.setBackgroundColor(getResources().getColor(R.color.blue));
        else if (PrefUtils.getAppLang(getActivity()).equals(PrefUtils.ARABIC_LANG))
            englishBtn.setBackgroundColor(getResources().getColor(R.color.blue));

        englishBtn.setOnClickListener(view -> ((MainActivity) getActivity()).changeLanguage(PrefUtils.ENGLISH_LANG));
        arabicBtn.setOnClickListener(view -> ((MainActivity) getActivity()).changeLanguage(PrefUtils.ARABIC_LANG));

    }


}
