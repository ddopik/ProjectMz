package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarYear;
import com.spade.mazda.ui.general.view.interfaces.CarYearInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class CarYearsAdapter extends RecyclerView.Adapter<CarYearsAdapter.YearsViewHolder> {

    private List<CarYear> carYears;
    private Context context;
    private CarYearInterface yearActions;

    public CarYearsAdapter(List<CarYear> carYears, Context context) {
        this.carYears = carYears;
        this.context = context;
    }

    @Override
    public YearsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new YearsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YearsViewHolder holder, int position) {
        CarYear carYear = carYears.get(position);
        holder.itemTitle.setText(carYear.getYearName());
        holder.itemView.setOnClickListener(view -> yearActions.onYearSelected(carYear));
    }

    @Override
    public int getItemCount() {
        return carYears.size();
    }

    public void setYearActions(CarYearInterface yearActions) {
        this.yearActions = yearActions;
    }


    class YearsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public YearsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
