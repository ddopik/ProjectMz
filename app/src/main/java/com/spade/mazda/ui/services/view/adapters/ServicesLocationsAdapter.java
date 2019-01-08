package com.spade.mazda.ui.services.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.ServicesLocation;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/10/17.
 */

public class ServicesLocationsAdapter extends RecyclerView.Adapter<ServicesLocationsAdapter.LocationsViewHolder> {
    private Context context;
    private List<ServicesLocation> servicesLocations;

    public ServicesLocationsAdapter(Context context, List<ServicesLocation> servicesLocations) {
        this.context = context;
        this.servicesLocations = servicesLocations;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.services_location_item, parent, false);
        return new LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        ServicesLocation servicesLocation = servicesLocations.get(position);
        holder.locationName.setText(servicesLocation.getLocation().getName());
        holder.locationDistrict.setText(servicesLocation.getDistrict());
        String timesText = String.format(context.getString(R.string.dash), servicesLocation.getStartTime(), servicesLocation.getEndTime());
        holder.startDayTextView.setText(servicesLocation.getStartDay());
        holder.endDayTextView.setText(servicesLocation.getEndDay());
        holder.timesTextView.setText(timesText);
    }

    @Override
    public int getItemCount() {
        return servicesLocations.size();
    }

    class LocationsViewHolder extends RecyclerView.ViewHolder {

        private CustomTextView locationName, locationDistrict, startDayTextView, endDayTextView, timesTextView;

        public LocationsViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.location_name);
            locationDistrict = itemView.findViewById(R.id.location_district);
            startDayTextView = itemView.findViewById(R.id.start_day_text_view);
            endDayTextView = itemView.findViewById(R.id.end_day_text_view);
            timesTextView = itemView.findViewById(R.id.times_text_view);
        }
    }
}
