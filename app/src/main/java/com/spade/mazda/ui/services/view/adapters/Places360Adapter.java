package com.spade.mazda.ui.services.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.Place360;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class Places360Adapter extends RecyclerView.Adapter<Places360Adapter.PlacesViewHolder> {

    private Context mContext;
    private List<Place360> place360List;

    public Places360Adapter(Context mContext, List<Place360> place360List) {
        this.mContext = mContext;
        this.place360List = place360List;
    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.nearby_branch_item, parent, false);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        Place360 place360 = place360List.get(position);
        holder.placeNameTextView.setText(place360.getName());
        holder.placeDescriptionTextView.setText(place360.getDescription());
    }

    @Override
    public int getItemCount() {
        return place360List.size();
    }

    class PlacesViewHolder extends RecyclerView.ViewHolder {
        private TextView placeNameTextView, placeDescriptionTextView, getDirectionsTextView;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.place_title);
            placeDescriptionTextView = itemView.findViewById(R.id.place_address);
            getDirectionsTextView = itemView.findViewById(R.id.get_direction);
        }
    }
}
