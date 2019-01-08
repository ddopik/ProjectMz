package com.spade.mazda.ui.mazda_club.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 6/19/17.
 */

public class MazdaClubPagerAdapter extends PagerAdapter {
    private static final String GOLDEN_TYPE = "golden";
    private static final String SILVER_TYPE = "silver";
    private static final String BLUE_TYPE = "blue";
    private static final String PLATINUM_TYPE = "black";

    private List<MazdaClubData> mazdaClubDataList;
    private Context mContext;


    public MazdaClubPagerAdapter(Context context, List<MazdaClubData> mazdaClubDataList) {
        mContext = context;
        this.mazdaClubDataList = mazdaClubDataList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MazdaClubData mazdaClub = mazdaClubDataList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_mazda_club, container,
                false);
        LinearLayout containerLayout = itemView.findViewById(R.id.container_layout);
        CustomTextView tierTitle = itemView.findViewById(R.id.tier_title);
        CustomTextView tierSubscriptionValue = itemView.findViewById(R.id.tier_subscription_value);
        CustomTextView tierLaborCost = itemView.findViewById(R.id.labor_cost_value);
        CustomTextView sparePartsValue = itemView.findViewById(R.id.spare_parts_value);
        CustomTextView fixologyValue = itemView.findViewById(R.id.fixology_value);
        CustomTextView fabricaValue = itemView.findViewById(R.id.fabrika_value);
        CustomTextView fromTotalLoanValue = itemView.findViewById(R.id.from_total_loan_value);
        CustomTextView laborCost360 = itemView.findViewById(R.id.labor_cost_value_360);
        CustomTextView productsValue = itemView.findViewById(R.id.products_value);

        tierTitle.setText(mazdaClub.getTitle());
        tierSubscriptionValue.setText(mazdaClub.getDescription());
        tierLaborCost.setText(mazdaClub.getLaborCost());
        sparePartsValue.setText(mazdaClub.getSpareParts());
        fixologyValue.setText(mazdaClub.getFixology());
        fabricaValue.setText(mazdaClub.getFabrika());
        fromTotalLoanValue.setText(mazdaClub.getFromTotalLoan());
        laborCost360.setText(mazdaClub.getLaborCoast());
        productsValue.setText(mazdaClub.getProducts());
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
        containerLayout.setBackground(drawable);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return mazdaClubDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
