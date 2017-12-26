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

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.general.view.adapters.CarYearsAdapter;
import com.spade.mazda.ui.general.view.interfaces.CarYearInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class YearsDialogFragment extends DialogFragment implements CarYearInterface {

    private CarYearInterface carYearInterface;
    private List<CarYear> carYears;

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
        CustomTextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.choose_car_year));
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        CarYearsAdapter yearsAdapter = new CarYearsAdapter(carYears, getContext());
        yearsAdapter.setYearActions(this);
        modelsRecyclerView.setAdapter(yearsAdapter);
    }

    public void setCarYearInterface(CarYearInterface carYearInterface) {
        this.carYearInterface = carYearInterface;
    }

    @Override
    public void onYearSelected(CarYear carYear) {
        carYearInterface.onYearSelected(carYear);
        dismiss();
    }

    public void setCarYears(List<CarYear> carYears) {
        this.carYears = carYears;
    }
}
