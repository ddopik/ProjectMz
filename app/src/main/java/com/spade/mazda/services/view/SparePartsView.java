package com.spade.mazda.services.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.services.model.SparePartCategory;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public interface SparePartsView extends BaseView {
    void showSparParts(List<SparePartCategory> sparePartCategories);

    void showLoading();

    void hideLoading();
}
