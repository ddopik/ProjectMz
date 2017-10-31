package com.spade.mazda.settings;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import com.spade.mazda.R;

/**
 * Created by Ayman Abouzeid on 10/31/17.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        this.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTextColor(ContextCompat.getColor(context, R.color.white));
    }
}
