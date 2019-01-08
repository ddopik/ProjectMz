package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.find_us.model.City;
import com.spade.mazda.ui.general.view.interfaces.CitiesInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ColorsViewHolder> {

    private List<City> cityList;
    private Context context;
    private CitiesInterface citiesInterface;

    public CitiesAdapter(List<City> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorsViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.itemTitle.setText(city.getCityName());
        holder.itemView.setOnClickListener(view -> citiesInterface.onCitySelected(city));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setCitiesInterface(CitiesInterface citiesInterface) {
        this.citiesInterface = citiesInterface;
    }

    class ColorsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
