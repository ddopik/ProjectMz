package com.spade.mazda.ui.services.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.SparePart;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsAdapter extends RecyclerView.Adapter<SparePartsAdapter.SparePartsViewHolder> {


    private Context context;
    private List<SparePart> sparePartList;

    public SparePartsAdapter(Context context, List<SparePart> sparePartList) {
        this.context = context;
        this.sparePartList = sparePartList;
    }

    @Override
    public SparePartsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spare_parts_item, parent, false);
        return new SparePartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SparePartsViewHolder holder, int position) {
        SparePart sparePart = sparePartList.get(position);
        holder.sparePartTitle.setText(sparePart.getSparePartTitle());
        holder.sparePartDescription.setText(sparePart.getSparePartDescription());
        holder.sparePartPrice.setText(String.format(context.getString(R.string.currency), String.valueOf(sparePart.getSparePartPrice())));
        if (sparePart.getIsAvailable() > 0) {
            holder.isAvailable.setVisibility(View.VISIBLE);
        } else {
            holder.isAvailable.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return sparePartList.size();
    }

    class SparePartsViewHolder extends RecyclerView.ViewHolder {

        TextView sparePartTitle, sparePartDescription, sparePartPrice, isAvailable;

        public SparePartsViewHolder(View itemView) {
            super(itemView);
            sparePartTitle = itemView.findViewById(R.id.spare_part_title);
            sparePartDescription = itemView.findViewById(R.id.spare_part_brief);
            sparePartPrice = itemView.findViewById(R.id.spare_part_price);
            isAvailable = itemView.findViewById(R.id.available_in_stock);
        }
    }
}
