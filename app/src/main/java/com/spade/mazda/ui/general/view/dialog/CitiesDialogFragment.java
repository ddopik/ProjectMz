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
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.general.view.adapters.CitiesAdapter;
import com.spade.mazda.ui.general.view.interfaces.CitiesInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 * VC4
 */

public class CitiesDialogFragment extends DialogFragment implements CitiesInterface {

    private CitiesInterface citiesInterface;
    private List<City> cities;

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
        title.setText(getString(R.string.select_city));
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        CitiesAdapter citiesAdapter = new CitiesAdapter(cities, getContext());
        citiesAdapter.setCitiesInterface(this);
        modelsRecyclerView.setAdapter(citiesAdapter);
    }

    public void setCitiesInterface(CitiesInterface citiesInterface) {
        this.citiesInterface = citiesInterface;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }


    @Override
    public void onCitySelected(City city) {
        citiesInterface.onCitySelected(city);
        dismiss();
    }
}
