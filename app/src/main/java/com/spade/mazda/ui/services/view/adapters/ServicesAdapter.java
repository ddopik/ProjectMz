package com.spade.mazda.ui.services.view.adapters;

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

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {
    private Context mContext;
    private TypedArray typedArray;
    private OnServiceClicked onServiceClicked;

    public ServicesAdapter(Context mContext, TypedArray typedArray) {
        this.mContext = mContext;
        this.typedArray = typedArray;
    }

    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.services_item_layout, parent, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesViewHolder holder, final int position) {
        holder.serviceTitle.setText(mContext.getResources().getStringArray(R.array.after_sales_services_title)[position]);
        holder.serviceImage.setImageResource(typedArray.getResourceId(position, 0));
        holder.itemView.setOnClickListener(v -> onServiceClicked.onServiceClicked(position));
//        FontUtils.overrideFonts(mContext, holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mContext.getResources().getStringArray(R.array.after_sales_services_title).length;
    }

    public void setOnServiceClicked(OnServiceClicked onServiceClicked) {
        this.onServiceClicked = onServiceClicked;
    }

    public interface OnServiceClicked {
        void onServiceClicked(int position);
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder {
        TextView serviceTitle, serviceDescription;
        ImageView serviceImage;

        public ServicesViewHolder(View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_title);
            serviceDescription = itemView.findViewById(R.id.service_description);
            serviceImage = itemView.findViewById(R.id.service_image);
        }
    }
}
