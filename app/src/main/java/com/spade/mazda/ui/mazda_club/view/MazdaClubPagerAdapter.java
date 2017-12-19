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

import com.spade.mazda.R;
import com.spade.mazda.ui.mazda_club.model.GoldenTier;
import com.spade.mazda.ui.welcome.model.IntroSlide;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 6/19/17.
 */

public class MazdaClubPagerAdapter extends PagerAdapter {
    private List<IntroSlide> introSlides;
    private Context mContext;


    public MazdaClubPagerAdapter(Context context, List<IntroSlide> introSlides) {
        mContext = context;
        this.introSlides = introSlides;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GoldenTier goldenTier = new GoldenTier();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_mazda_club, container,
                false);
        LinearLayout containerLayout = itemView.findViewById(R.id.container_layout);
        TextView tierTitle = itemView.findViewById(R.id.tier_title);
        TextView tierSubscriptionValue = itemView.findViewById(R.id.tier_subscription_value);
        TextView tierLaborCost = itemView.findViewById(R.id.labor_cost_value);
        TextView sparePartsValue = itemView.findViewById(R.id.spare_parts_value);
        TextView fixologyValue = itemView.findViewById(R.id.fixology_value);
        TextView fabricaValue = itemView.findViewById(R.id.fabrika_value);
        TextView fromTotalLoanValue = itemView.findViewById(R.id.from_total_loan_value);
        TextView laborCost360 = itemView.findViewById(R.id.labor_cost_value_360);
        TextView productsValue = itemView.findViewById(R.id.products_value);

        tierTitle.setText(goldenTier.getTitle());
        tierSubscriptionValue.setText(goldenTier.getDescription());
        tierLaborCost.setText(goldenTier.getLaborCost());
        sparePartsValue.setText(goldenTier.getSpareParts());
        fixologyValue.setText(goldenTier.getFixology());
        fabricaValue.setText(goldenTier.getFabrika());
        fromTotalLoanValue.setText(goldenTier.getFromTotalLoan());
        laborCost360.setText(goldenTier.getLaborCoast());
        productsValue.setText(goldenTier.getProducts());

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.gradient_background);
        containerLayout.setBackground(drawable);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return introSlides.size();
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
