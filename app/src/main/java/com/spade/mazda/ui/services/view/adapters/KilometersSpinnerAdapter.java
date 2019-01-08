package com.spade.mazda.ui.services.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.Kilometer;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class KilometersSpinnerAdapter extends BaseAdapter {
    private List<Kilometer> kilometers;
    private Context mContext;

    public KilometersSpinnerAdapter(List<Kilometer> kilometers, Context mContext) {
        this.kilometers = kilometers;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return kilometers.size();
    }

    @Override
    public Object getItem(int position) {
        return kilometers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kilometer kilometer = kilometers.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemText.setText(kilometer.getName());
//        FontUtils.overrideFonts(mContext, row);
        return view;
    }

    private class ViewHolder {
        TextView itemText;

        ViewHolder(View v) {
            itemText = v.findViewById(R.id.item_title);
        }

    }
}
