package com.spade.mazda.ui.cars.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.Specification;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public class CarSpecsAdapter extends RecyclerView.Adapter<CarSpecsAdapter.SpecsViewHolder> {

    private Context context;
    private List<Specification> specificationList;

    public CarSpecsAdapter(Context context, List<Specification> specificationList) {
        this.context = context;
        this.specificationList = specificationList;
    }

    @Override
    public SpecsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.specs_attribute_item_layout, parent, false);
        return new SpecsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecsViewHolder holder, int position) {
        Specification specification = specificationList.get(position);
        String attributeName = specification.getName();
        String attributeValue = specification.getSpecifications();
        holder.attributeName.setText(attributeName);
        holder.attributeValue.setText(attributeValue);
    }

    @Override
    public int getItemCount() {
        return specificationList.size();
    }

    class SpecsViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView attributeName, attributeValue;

        public SpecsViewHolder(View itemView) {
            super(itemView);
            attributeName = itemView.findViewById(R.id.attribute_name);
            attributeValue = itemView.findViewById(R.id.attribute_value);

        }
    }
}
