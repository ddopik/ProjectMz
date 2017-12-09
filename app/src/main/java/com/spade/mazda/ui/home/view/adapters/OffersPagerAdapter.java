package com.spade.mazda.ui.home.view.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spade.mazda.R;
import com.spade.mazda.ui.home.model.Offer;
import com.spade.mazda.utils.GlideApp;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 6/19/17.
 */

public class OffersPagerAdapter extends PagerAdapter {
    private List<Offer> offers;
    private Context mContext;


    public OffersPagerAdapter(Context context, List<Offer> offers) {
        mContext = context;
        this.offers = offers;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Offer offer = offers.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.offers_item, container,
                false);
        ImageView offerImage = itemView.findViewById(R.id.offer_image);

        GlideApp.with(mContext).load(offer.getImage()).centerCrop().into(offerImage);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return offers.size();
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
