package com.spade.mazda.ui.services.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.services.view.interfaces.RequestSparePartsView;

/**
 * Created by Ayman Abouzeid on 12/15/17.
 */

public interface RequestSparePartsPresenter extends BasePresenter<RequestSparePartsView> {
    void getSparePartsOutlets(String type);

    void getSpareParts();

    void requestSparePart(String sparePartCategoryID, String sparePartId, String branchID, String description);
}
