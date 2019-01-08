package com.spade.mazda.ui.general.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.SparePart;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class SparePartsSpinnerAdapter extends BaseAdapter {
    private List<SparePart> spareParts;
    private Context mContext;

    public SparePartsSpinnerAdapter(List<SparePart> spareParts, Context mContext) {
        this.spareParts = spareParts;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return spareParts.size();
    }

    @Override
    public Object getItem(int position) {
        return spareParts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SparePart sparePart = spareParts.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemText.setText(sparePart.getSparePartTitle());
        return view;
    }

    private class ViewHolder {
        TextView itemText;

        ViewHolder(View v) {
            itemText = v.findViewById(R.id.item_title);
        }
    }
}
