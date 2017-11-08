package com.spade.mazda.services.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.services.model.SparePartCategory;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsCategoriesAdapter extends RecyclerView.Adapter<SparePartsCategoriesAdapter.SparPartsCategoriesHolder> {

    private Context context;
    private List<SparePartCategory> sparePartCategories;

    public SparePartsCategoriesAdapter(Context context, List<SparePartCategory> sparePartCategories) {
        this.context = context;
        this.sparePartCategories = sparePartCategories;
    }

    @Override
    public SparPartsCategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spare_parts_categories_item, parent, false);
        return new SparPartsCategoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(SparPartsCategoriesHolder holder, int position) {
        SparePartCategory sparePartCategory = sparePartCategories.get(position);
        SparePartsAdapter sparePartsAdapter = new SparePartsAdapter(context, sparePartCategory.getSparePartList());
        holder.sparePartsRecycler.setAdapter(sparePartsAdapter);
        holder.categoryTitle.setText(sparePartCategory.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return sparePartCategories.size();
    }

    class SparPartsCategoriesHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        CustomRecyclerView sparePartsRecycler;

        public SparPartsCategoriesHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            sparePartsRecycler = itemView.findViewById(R.id.spare_parts_recycler_view);
        }
    }
}
