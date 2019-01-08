package com.spade.mazda.ui.services.view.interfaces;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.services.model.SparePartCategory;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/15/17.
 */

public interface RequestSparePartsView extends BaseView {
    void showSparePartsCategories(List<SparePartCategory> sparePartCategories);

    void showSparePartsOutLets(List<Branch> sparePartsOutlets);

    void showConfirmationDialog();

    void showProgressDialog();

    void hideProgressDialog();

    void finish();
}
