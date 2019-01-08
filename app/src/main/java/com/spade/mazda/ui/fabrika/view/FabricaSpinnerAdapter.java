package com.spade.mazda.ui.fabrika.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.fabrika.model.FabricaData;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class FabricaSpinnerAdapter extends BaseAdapter {
    private List<FabricaData> fabricaDataList;
    private Context mContext;
    private boolean isBrand = false;

    public FabricaSpinnerAdapter(List<FabricaData> fabricaDataList, Context mContext, boolean isBrand) {
        this.fabricaDataList = fabricaDataList;
        this.mContext = mContext;
        this.isBrand = isBrand;
    }

    @Override
    public int getCount() {
        return fabricaDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return fabricaDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FabricaData fabricaData = fabricaDataList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        if (isBrand) {
            viewHolder.itemText.setText(fabricaData.getBrandName());
        } else {
            viewHolder.itemText.setText(fabricaData.getName());
        }
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
