package com.spade.mazda.ui.cars.view.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.mazda.R;


/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class CarActionsAdapter extends RecyclerView.Adapter<CarActionsAdapter.CarActionsViewHolder> {
    private Context mContext;
    private TypedArray typedArray;
    private OnActionClicked onActionClicked;

    public CarActionsAdapter(Context mContext, TypedArray typedArray) {
        this.mContext = mContext;
        this.typedArray = typedArray;
    }

    @Override
    public CarActionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.action_item, parent, false);
        return new CarActionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarActionsViewHolder holder, int position) {
        holder.actionTitle.setText(mContext.getResources().getStringArray(R.array.action_titles)[position]);
        holder.actionImage.setImageResource(typedArray.getResourceId(position, 0));
        holder.itemView.setOnClickListener(v -> onActionClicked.onActionClicked(position));
//        FontUtils.overrideFonts(mContext, holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mContext.getResources().getStringArray(R.array.action_titles).length;
    }

    public void setOnActionClicked(OnActionClicked onActionClicked) {
        this.onActionClicked = onActionClicked;
    }

    public interface OnActionClicked {
        void onActionClicked(int position);
    }

    class CarActionsViewHolder extends RecyclerView.ViewHolder {
        TextView actionTitle;
        ImageView actionImage;

        public CarActionsViewHolder(View itemView) {
            super(itemView);
            actionTitle = itemView.findViewById(R.id.action_title);
            actionImage = itemView.findViewById(R.id.action_image);
        }
    }
}
