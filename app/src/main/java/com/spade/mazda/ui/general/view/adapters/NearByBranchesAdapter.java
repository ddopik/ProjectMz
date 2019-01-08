package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.interfaces.BranchesInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class NearByBranchesAdapter extends RecyclerView.Adapter<NearByBranchesAdapter.ColorsViewHolder> {

    private List<Branch> branches;
    private Context context;
    private BranchesInterface branchesInterface;

    public NearByBranchesAdapter(List<Branch> branches, Context context) {
        this.branches = branches;
        this.context = context;
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorsViewHolder holder, int position) {
        Branch branch = branches.get(position);
        holder.itemTitle.setText(branch.getName());
        holder.itemView.setOnClickListener(view -> branchesInterface.onBranchSelected(branch));
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public void setBranchesInterface(BranchesInterface branchesInterface) {
        this.branchesInterface = branchesInterface;
    }

    class ColorsViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
