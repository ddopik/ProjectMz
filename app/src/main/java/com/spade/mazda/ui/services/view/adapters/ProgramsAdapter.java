package com.spade.mazda.ui.services.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;


/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder> {
    private Context mContext;
    private List<Program> programs;

    public ProgramsAdapter(Context mContext, List<Program> programs) {
        this.mContext = mContext;
        this.programs = programs;
    }


    @Override
    public ProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.program_item, parent, false);
        return new ProgramsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramsViewHolder holder, final int position) {
        Program program = programs.get(position);
        holder.programTitle.setText(program.getTitle());
        holder.programDescription.setText(program.getDescription());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }


    class ProgramsViewHolder extends RecyclerView.ViewHolder {
        TextView programTitle, programDescription;

        public ProgramsViewHolder(View itemView) {
            super(itemView);
            programTitle = itemView.findViewById(R.id.program_title);
            programDescription = itemView.findViewById(R.id.program_description);
        }
    }
}
