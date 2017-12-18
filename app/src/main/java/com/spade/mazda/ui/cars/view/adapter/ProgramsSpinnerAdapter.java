package com.spade.mazda.ui.cars.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/29/17.
 */

public class ProgramsSpinnerAdapter extends BaseAdapter {
    private List<Program> programs;
    private Context mContext;

    public ProgramsSpinnerAdapter(List<Program> programs, Context mContext) {
        this.programs = programs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return programs.size();
    }

    @Override
    public Object getItem(int position) {
        return programs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Program program = programs.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemText.setText(program.getTitle());
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
