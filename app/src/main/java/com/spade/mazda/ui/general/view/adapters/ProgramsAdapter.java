package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.general.view.interfaces.ProgramsInterface;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder> {

    private List<Program> programList;
    private Context context;
    private ProgramsInterface programsInterface;

    public ProgramsAdapter(List<Program> programList, Context context) {
        this.programList = programList;
        this.context = context;
    }

    @Override
    public ProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new ProgramsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramsViewHolder holder, int position) {
        Program program = programList.get(position);
        holder.itemTitle.setText(program.getTitle());
        holder.itemView.setOnClickListener(view -> programsInterface.onProgramSelected(program));
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    public void setProgramsActions(ProgramsInterface programsInterface) {
        this.programsInterface = programsInterface;
    }

    class ProgramsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public ProgramsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
