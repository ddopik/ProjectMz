package com.spade.mazda.ui.find_us.view.interfaces;

import com.google.android.gms.maps.model.LatLng;
import com.spade.mazda.base.BaseView;
import com.spade.mazda.ui.find_us.model.Branch;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface BranchesView extends BaseView {
    void showBranches(List<Branch> branchList);

    void showPins(List<LatLng> latLngList);

    void showLoading();

    void hideLoading();
}
