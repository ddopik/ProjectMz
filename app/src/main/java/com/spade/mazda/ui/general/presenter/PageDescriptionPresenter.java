package com.spade.mazda.ui.general.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.general.view.PageDescriptionView;

/**
 * Created by Ayman Abouzeid on 12/17/17.
 */

public interface PageDescriptionPresenter extends BasePresenter<PageDescriptionView> {
    void getPageDescription(String pageID);
}
