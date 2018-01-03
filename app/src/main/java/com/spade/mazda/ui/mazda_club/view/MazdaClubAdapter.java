package com.spade.mazda.ui.mazda_club.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class MazdaClubAdapter extends RecyclerView.Adapter<MazdaClubAdapter.BranchesViewHolder> {

    private static final String GOLDEN_TYPE = "golden";
    private static final String SILVER_TYPE = "silver";
    private static final String BLUE_TYPE = "blue";
    private static final String PLATINUM_TYPE = "black";
    private Context mContext;
    private List<MazdaClubData> mazdaClubDataList;

    public MazdaClubAdapter(Context mContext, List<MazdaClubData> mazdaClubDataList) {
        this.mContext = mContext;
        this.mazdaClubDataList = mazdaClubDataList;
    }

    @Override
    public BranchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_mazda_club, parent, false);
        return new BranchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BranchesViewHolder holder, int position) {
        MazdaClubData mazdaClub = mazdaClubDataList.get(position);
        holder.tierTitle.setText(mazdaClub.getTitle());
        holder.tierSubscriptionValue.setText(mazdaClub.getDescription());
        holder.tierLaborCost.setText(mazdaClub.getLaborCost());
        holder.sparePartsValue.setText(mazdaClub.getSpareParts());
        holder.fixologyValue.setText(mazdaClub.getFixology());
        holder.fabricaValue.setText(mazdaClub.getFabrika());
        holder.fromTotalLoanValue.setText(mazdaClub.getFromTotalLoan());
        holder.laborCost360.setText(mazdaClub.getLaborCoast());
        holder.productsValue.setText(mazdaClub.getProducts());
        int drawableResId = 0;
        switch (mazdaClub.getType()) {
            case GOLDEN_TYPE:
                drawableResId = R.drawable.tier_golden_gradient_background;
                break;
            case SILVER_TYPE:
                drawableResId = R.drawable.tier_silver_gradient_background;
                break;
            case BLUE_TYPE:
                drawableResId = R.drawable.tier_blue_gradient_background;
                break;
            case PLATINUM_TYPE:
                drawableResId = R.drawable.tier_platinum_gradient_background;
                break;

        }
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableResId);
        holder.containerLayout.setBackground(drawable);
    }

    @Override
    public int getItemCount() {
        return mazdaClubDataList.size();
    }

    class BranchesViewHolder extends RecyclerView.ViewHolder {
        CustomTextView tierTitle, tierSubscriptionValue, tierLaborCost,
                sparePartsValue, fixologyValue, fabricaValue,
                fromTotalLoanValue, laborCost360, productsValue;
        LinearLayout containerLayout;

        public BranchesViewHolder(View itemView) {
            super(itemView);
            containerLayout = itemView.findViewById(R.id.container_layout);
            tierTitle = itemView.findViewById(R.id.tier_title);
            tierSubscriptionValue = itemView.findViewById(R.id.tier_subscription_value);
            tierLaborCost = itemView.findViewById(R.id.labor_cost_value);
            sparePartsValue = itemView.findViewById(R.id.spare_parts_value);
            fixologyValue = itemView.findViewById(R.id.fixology_value);
            fabricaValue = itemView.findViewById(R.id.fabrika_value);
            fromTotalLoanValue = itemView.findViewById(R.id.from_total_loan_value);
            laborCost360 = itemView.findViewById(R.id.labor_cost_value_360);
            productsValue = itemView.findViewById(R.id.products_value);
        }
    }
}
