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
import com.spade.mazda.base.DataSource;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.general.view.adapters.CarModelsAdapter;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class ModelsDialogFragment extends DialogFragment implements CarModelInterface {

    private CarModelInterface modelActions;

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
        DataSource dataSource = DataSource.getInstance();
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        CarModelsAdapter carModelsAdapter = new CarModelsAdapter(dataSource.getCarModelList(), getContext());
        carModelsAdapter.setModelActions(this);
        modelsRecyclerView.setAdapter(carModelsAdapter);
    }

    @Override
    public void onModelSelected(CarModel carModel) {
        modelActions.onModelSelected(carModel);
        dismiss();
    }


    public void setModelActions(CarModelInterface modelActions) {
        this.modelActions = modelActions;
    }
}
