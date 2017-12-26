package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.general.view.interfaces.CarModelInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class CarModelsAdapter extends RecyclerView.Adapter<CarModelsAdapter.ModelsViewHolder> {

    private List<CarModel> carModels;
    private Context context;
    private CarModelInterface modelActions;

    public CarModelsAdapter(List<CarModel> carModels, Context context) {
        this.carModels = carModels;
        this.context = context;
    }

    @Override
    public ModelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new ModelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModelsViewHolder holder, int position) {
        CarModel carModel = carModels.get(position);
        holder.itemTitle.setText(carModel.getCarModelName());
        holder.itemView.setOnClickListener(view -> modelActions.onModelSelected(carModel));
    }

    @Override
    public int getItemCount() {
        return carModels.size();
    }

    public void setModelActions(CarModelInterface modelActions) {
        this.modelActions = modelActions;
    }

//    public interface ModelActions {
//        void onModelSelected(CarModel carModel);
//    }

    class ModelsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public ModelsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
