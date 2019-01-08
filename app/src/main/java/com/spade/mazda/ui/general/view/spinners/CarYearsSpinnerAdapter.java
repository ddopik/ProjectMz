package com.spade.mazda.ui.general.view.spinners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarYear;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class CarYearsSpinnerAdapter extends BaseAdapter {
    private List<CarYear> carYears;
    private Context mContext;

    public CarYearsSpinnerAdapter(List<CarYear> carYears, Context mContext) {
        this.carYears = carYears;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return carYears.size();
    }

    @Override
    public Object getItem(int position) {
        return carYears.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CarYear carYear = carYears.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemText.setText(carYear.getYearName());
//        FontUtils.overrideFonts(mContext, row);
        return view;
    }

    private class ViewHolder {
        TextView itemText;

        ViewHolder(View v) {
            itemText = v.findViewById(R.id.item_title);
        }

    }
}
