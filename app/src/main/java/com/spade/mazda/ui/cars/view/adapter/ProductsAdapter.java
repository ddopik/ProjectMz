package com.spade.mazda.ui.cars.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.utils.GlideApp;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<CarModel> carModelList;
    private Context mContext;
    private OnCarSelected onCarSelected;

    public ProductsAdapter(List<CarModel> carModelList, Context mContext) {
        this.carModelList = carModelList;
        this.mContext = mContext;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.car_model_item, parent, false);

        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        CarModel carModel = carModelList.get(position);
        holder.modelTitle.setText(carModel.getCarModelName());
        holder.modelDescription.setText(carModel.getCarModelDescription());
        GlideApp.with(mContext).load(carModel.getCarModelImage()).into(holder.carModelImage);
        holder.itemView.setOnClickListener(view -> onCarSelected.onCarSelected(String.valueOf(carModel.getCarModelId()), carModel.getCarModelName()));
    }

    @Override
    public int getItemCount() {
        return carModelList.size();
    }

    public void setOnCarSelected(OnCarSelected onCarSelected) {
        this.onCarSelected = onCarSelected;
    }

    public interface OnCarSelected {
        void onCarSelected(String carId, String carName);
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView modelTitle, modelDescription, viewCarDetails;
        ImageView carModelImage;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            modelTitle = itemView.findViewById(R.id.model_title);
            modelDescription = itemView.findViewById(R.id.model_description);
            carModelImage = itemView.findViewById(R.id.model_image);
            viewCarDetails = itemView.findViewById(R.id.view_car_details);
        }


    }
}
