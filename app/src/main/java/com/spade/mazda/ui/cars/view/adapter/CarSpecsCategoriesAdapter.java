package com.spade.mazda.ui.cars.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.Category;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/4/17.
 */

public class CarSpecsCategoriesAdapter extends RecyclerView.Adapter<CarSpecsCategoriesAdapter.SpecsViewHolder> {

    private Context context;
    private List<Category> specsCategoriesList;

    public CarSpecsCategoriesAdapter(Context context, List<Category> specsCategoriesList) {
        this.context = context;
        this.specsCategoriesList = specsCategoriesList;
    }

    @Override
    public SpecsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.specs_header_item_layout, parent, false);
        return new SpecsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecsViewHolder holder, int position) {
        Category category = specsCategoriesList.get(position);
        String categoryName = category.getName();
        holder.categoryName.setText(categoryName);
        CarSpecsAdapter carSpecsAdapter = new CarSpecsAdapter(context, category.getSpecifications());
        holder.categorySpecsRecycler.setAdapter(carSpecsAdapter);
    }

    @Override
    public int getItemCount() {
        return specsCategoriesList.size();
    }

    class SpecsViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView categoryName;
        private CustomRecyclerView categorySpecsRecycler;

        public SpecsViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.specs_category_title);
            categorySpecsRecycler = itemView.findViewById(R.id.category_specs_recycler_view);
        }
    }
}
