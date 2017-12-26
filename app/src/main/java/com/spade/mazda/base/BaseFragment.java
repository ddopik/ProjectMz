package com.spade.mazda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.spade.mazda.ui.general.view.dialog.ConfirmationDialogFragment;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public abstract class BaseFragment extends Fragment {

    private MazdaProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    public void showMazdaProgressDialog(int resID) {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(resID);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }

    public void hideMazdaProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void showConfirmationDialog(int resID) {
        ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
        confirmationDialogFragment.setConfirmationMessage(getString(resID));
        confirmationDialogFragment.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }

    protected abstract void initPresenter();

    protected abstract void initViews();
}
