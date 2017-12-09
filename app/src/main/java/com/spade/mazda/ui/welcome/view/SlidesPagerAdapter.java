package com.spade.mazda.ui.welcome.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.ui.welcome.model.IntroSlide;
import com.spade.mazda.utils.GlideApp;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 6/19/17.
 */

public class SlidesPagerAdapter extends PagerAdapter {
    private List<IntroSlide> introSlides;
    private Context mContext;


    public SlidesPagerAdapter(Context context, List<IntroSlide> introSlides) {
        mContext = context;
        this.introSlides = introSlides;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        IntroSlide introSlide = introSlides.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.intro_slide_item, container,
                false);
        ImageView slideImage = itemView.findViewById(R.id.slide_image);
        TextView slideTitle = itemView.findViewById(R.id.slideTitle);
        TextView slideDescription = itemView.findViewById(R.id.slide_description);
        slideTitle.setText(introSlide.getTitle());
        slideDescription.setText(introSlide.getDescription());
        GlideApp.with(mContext).load(introSlide.getImage()).into(slideImage);

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
