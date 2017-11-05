package com.spade.mazda.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.spade.mazda.R;

/**
 * Created by Ayman Abouzeid on 11/5/17.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        styleText(context, attrs);
    }

    private void styleText(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView);
        int fontStyle = typedArray.getInteger(R.styleable.CustomTextView_fontStyle, 0);
        int fontNameStringID = 0;
        switch (fontStyle) {
            case 0:
                fontNameStringID = R.string.gotham_regular;
                break;
            case 1:
                fontNameStringID = R.string.gotham_semi_bold;
                break;
            case 2:
                fontNameStringID = R.string.gotham_bold;
                break;
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + getResources().getString(fontNameStringID));
        setTypeface(typeface);
        typedArray.recycle();
    }
}

