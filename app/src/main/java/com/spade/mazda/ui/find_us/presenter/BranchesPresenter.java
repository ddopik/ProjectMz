package com.spade.mazda.ui.find_us.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.interfaces.BranchesView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface BranchesPresenter extends BasePresenter<BranchesView> {

    void getBranches(int type, String appLang);

    Observable<LatLng> getMapPins(List<Branch> branchList);
}
