package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.ModelTrim;
import com.spade.mazda.ui.general.view.interfaces.CarTrimInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class CarTrimsAdapter extends RecyclerView.Adapter<CarTrimsAdapter.TrimsViewHolder> {

    private List<ModelTrim> modelTrims;
    private Context context;
    private CarTrimInterface trimActions;

    public CarTrimsAdapter(List<ModelTrim> modelTrims, Context context) {
        this.modelTrims = modelTrims;
        this.context = context;
    }

    @Override
    public TrimsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new TrimsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrimsViewHolder holder, int position) {
        ModelTrim modelTrim = modelTrims.get(position);
        holder.itemTitle.setText(modelTrim.getTrimName());
        holder.itemView.setOnClickListener(view -> trimActions.onTrimSelected(modelTrim));
    }

    @Override
    public int getItemCount() {
        return modelTrims.size();
    }

    public void setTrimActions(CarTrimInterface trimActions) {
        this.trimActions = trimActions;
    }


    class TrimsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public TrimsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
