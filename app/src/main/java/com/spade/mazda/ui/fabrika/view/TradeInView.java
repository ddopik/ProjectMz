package com.spade.mazda.ui.fabrika.view;

import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.fabrika.model.FabricaData;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public interface TradeInView extends BaseView {

    void showBrands(List<FabricaData> brandsList);

    void showModels(List<FabricaData> modelsList);

    void showBranches(List<FabricaData> branchesList);

    void showProgressDialog();

    void hideProgressDialog();

    void showConfirmationDialog();

    void finish();

}
