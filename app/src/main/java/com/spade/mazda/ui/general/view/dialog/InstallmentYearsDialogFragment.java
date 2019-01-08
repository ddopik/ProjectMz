package com.spade.mazda.ui.general.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.R;
import com.spade.mazda.ui.general.view.adapters.YearsNumberAdapter;
import com.spade.mazda.ui.general.view.interfaces.InterestRatesInterface;
import com.spade.mazda.ui.services.model.InterestRates;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class InstallmentYearsDialogFragment extends DialogFragment implements InterestRatesInterface {

    private InterestRatesInterface interestRatesInterface;
    private List<InterestRates> interestRatesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_listing, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        YearsNumberAdapter yearsNumberAdapter = new YearsNumberAdapter(interestRatesList, getContext());
        yearsNumberAdapter.setRatesActions(this);
        modelsRecyclerView.setAdapter(yearsNumberAdapter);
    }

    public void setInterestRatesInterface(InterestRatesInterface interestRatesInterface) {
        this.interestRatesInterface = interestRatesInterface;
    }

    public void setInterestRatesList(List<InterestRates> interestRatesList) {
        this.interestRatesList = interestRatesList;
    }

    @Override
    public void onRateSelected(InterestRates interestRate) {
        interestRatesInterface.onRateSelected(interestRate);
        dismiss();
    }
}
