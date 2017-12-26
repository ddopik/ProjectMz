package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.TrimColor;
import com.spade.mazda.ui.general.view.interfaces.CarColorInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class CarColorsAdapter extends RecyclerView.Adapter<CarColorsAdapter.ColorsViewHolder> {

    private List<TrimColor> trimColors;
    private Context context;
    private CarColorInterface colorsActions;

    public CarColorsAdapter(List<TrimColor> trimColors, Context context) {
        this.trimColors = trimColors;
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
        TrimColor trimColor = trimColors.get(position);
        holder.itemTitle.setText(trimColor.getColorName());
        holder.itemView.setOnClickListener(view -> colorsActions.onColorSelected(trimColor));
    }

    @Override
    public int getItemCount() {
        return trimColors.size();
    }

    public void setColorsActions(CarColorInterface colorsActions) {
        this.colorsActions = colorsActions;
    }

    class ColorsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
