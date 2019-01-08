package com.spade.mazda.ui.profile.view.interfaces;

import com.spade.mazda.base.BaseView;

/**
 * Created by Ayman Abouzeid on 12/27/17.
 */

public interface AddHistoryView extends BaseView {

    void setCommentError();

    void setDateError();

    void setDate(String date);

    void finish();
}
