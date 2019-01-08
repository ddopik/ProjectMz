package com.spade.mazda.ui.general.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.SparePartCategory;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class SparePartsCategoriesSpinnerAdapter extends BaseAdapter {
    private List<SparePartCategory> sparePartCategories;
    private Context mContext;

    public SparePartsCategoriesSpinnerAdapter(List<SparePartCategory> sparePartCategories, Context mContext) {
        this.sparePartCategories = sparePartCategories;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return sparePartCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return sparePartCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SparePartCategory sparePartCategory = sparePartCategories.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemText.setText(sparePartCategory.getCategoryName());
        return view;
    }

    private class ViewHolder {
        TextView itemText;

        ViewHolder(View v) {
            itemText = v.findViewById(R.id.item_title);
        }
    }
}
