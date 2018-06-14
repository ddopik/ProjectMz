package com.spade.mazda.ui.find_us.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchesViewHolder> {

    private Context mContext;
    private List<Branch> branchList;
    private GetDirectionAction getDirectionAction;

    public BranchesAdapter(Context mContext, List<Branch> branchList) {
        this.mContext = mContext;
        this.branchList = branchList;
    }

    @Override
    public BranchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.nearby_branch_item, parent, false);
        return new BranchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BranchesViewHolder holder, int position) {
        Branch branch = branchList.get(position);
        holder.placeNameTextView.setText(branch.getName());
        holder.placeDescriptionTextView.setText(branch.getBranchAddress());
        holder.getDirectionsTextView.setOnClickListener(view -> {
            getDirectionAction.OnGetDirectionLister(branch.getBranchLat(), branch.getBranchLng());
        });
        holder.placesItem.setOnClickListener(view -> {
                    getDirectionAction.onItemClickedListner(branch.getBranchLat(), branch.getBranchLng());
                }
        );
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public interface GetDirectionAction {
        void OnGetDirectionLister(String lat, String lng);

        void onItemClickedListner(String lat, String lng);
    }

    public void setGetDirectionAction(GetDirectionAction getDirectionAction) {
        this.getDirectionAction = getDirectionAction;
    }

    class BranchesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout placesItem;
        private TextView placeNameTextView, placeDescriptionTextView, getDirectionsTextView;

        public BranchesViewHolder(View itemView) {
            super(itemView);
            placesItem = itemView.findViewById(R.id.places_item);
            placeNameTextView = itemView.findViewById(R.id.place_title);
            placeDescriptionTextView = itemView.findViewById(R.id.place_address);
            getDirectionsTextView = itemView.findViewById(R.id.get_direction);
        }
    }
}
