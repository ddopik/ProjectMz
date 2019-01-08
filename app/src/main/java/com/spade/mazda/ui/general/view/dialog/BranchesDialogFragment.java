package com.spade.mazda.ui.general.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.adapters.NearByBranchesAdapter;
import com.spade.mazda.ui.general.view.interfaces.BranchesInterface;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 VC4
 */

public class BranchesDialogFragment extends DialogFragment implements BranchesInterface {

    private BranchesInterface branchesInterface;
    private List<Branch> branches;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_listing, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        CustomTextView title = view.findViewById(R.id.title);
        title.setText(getString(R.string.nearest_showrom));
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        NearByBranchesAdapter branchesAdapter = new NearByBranchesAdapter(branches, getContext());
        branchesAdapter.setBranchesInterface(this);
        modelsRecyclerView.setAdapter(branchesAdapter);
    }

    public void setBranchesInterface(BranchesInterface branchesInterface) {
        this.branchesInterface = branchesInterface;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public void onBranchSelected(Branch branch) {
        branchesInterface.onBranchSelected(branch);
        dismiss();
    }
}
