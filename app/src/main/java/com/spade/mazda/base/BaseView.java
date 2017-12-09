package com.spade.mazda.base;

import android.widget.EditText;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public interface BaseView {
    void showMessage(String message);

    void showMessage(int resID);

    void showLoading();

    void hideLoading();

    void setError(EditText editText, int resId);
}
