package com.spade.mazda.ui.services.view.adapters;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.AvailableTimeData;

import java.util.List;

/**
 * Created by abdalla_maged on 10/28/2018.
 */
public class AvailaibleTimesAdapter extends RecyclerView.Adapter<AvailaibleTimesAdapter.TagViewHolder> {
    private List<AvailableTimeData> availableTimeDataList;
    public OnSelectedItemClicked onSelectedItemClicked;
    private Context context;

    public AvailaibleTimesAdapter(List<AvailableTimeData> availableTimeDataList) {
        this.availableTimeDataList = availableTimeDataList;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        this.context=viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_holder_availaible_time, viewGroup, false);
        return new TagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder tagViewHolder, int i) {

        tagViewHolder.tagName.setText(availableTimeDataList.get(i).time);

        if (availableTimeDataList.get(i).isSelected){
            tagViewHolder.tagName.setBackground (context.getResources().getDrawable(R.drawable.rounded_availaible_background_dark));
        }else {
            tagViewHolder.tagName.setBackground (context.getResources().getDrawable(R.drawable.rounded_availaible_background_light));
        }

        if (onSelectedItemClicked != null) {
            tagViewHolder.tagName.setOnClickListener((view) -> onSelectedItemClicked.onItemClicked(availableTimeDataList.get(i)));

         }

    }

    @Override
    public int getItemCount() {

        return availableTimeDataList.size();
    }


    class TagViewHolder extends RecyclerView.ViewHolder {
        TextView tagName;


        TagViewHolder(View view) {
            super(view);
            tagName = view.findViewById(R.id.available_times_data);

        }

    }

    public interface OnSelectedItemClicked {
        void onItemClicked(AvailableTimeData availableTimeDataag);
     }
}
