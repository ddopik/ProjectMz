package com.spade.mazda.ui.find_us.presenter;

import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.find_us.view.interfaces.BranchesView;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface BranchesPresenter extends BasePresenter<BranchesView> {

    void getBranches(int type, String appLang);

    void getCities(List<Branch> branches);

    void getMapPins(List<Branch> branchList);

}
