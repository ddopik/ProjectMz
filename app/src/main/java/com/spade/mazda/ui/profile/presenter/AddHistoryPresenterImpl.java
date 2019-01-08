package com.spade.mazda.ui.profile.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.network.ApiHelper;
import com.spade.mazda.ui.authentication.view.dialogs.PickDateDialog;
import com.spade.mazda.ui.general.view.dialog.PickImageDialogFragment;
import com.spade.mazda.ui.profile.view.interfaces.AddHistoryView;
import com.spade.mazda.utils.PrefUtils;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 12/27/17.
 */

public class AddHistoryPresenterImpl implements AddHistoryPresenter, PickDateDialog.OnDateSet, PickImageDialogFragment.PickImageActions {
    private BaseFragment baseFragment;
    private Context context;
    private AddHistoryView addHistoryView;
    private String comment, date;
    private File file;

    public AddHistoryPresenterImpl(Context context, BaseFragment baseFragment) {
        this.context = context;
        this.baseFragment = baseFragment;
    }

    @Override
    public void setView(AddHistoryView view) {
        addHistoryView = view;
    }

    @Override
    public void showDatePickerDialog(FragmentManager fragmentManager) {
        PickDateDialog pickDateDialog = new PickDateDialog();
        pickDateDialog.setOnDateSet(this);
        pickDateDialog.show(fragmentManager, PickDateDialog.class.getSimpleName());
    }

    @Override
    public void pickImageFromCamera(BaseFragment baseFragment) {
        EasyImage.openCamera(baseFragment, 0);
    }

    @Override
    public void pickImageFromGallery(BaseFragment baseFragment) {
        EasyImage.openGallery(baseFragment, 0);
    }

    @Override
    public void addHistory() {
        addHistoryView.showLoading();
        ApiHelper.addHistory(PrefUtils.getAppLang(context),
                PrefUtils.getUserToken(context), comment, date, file, new ApiHelper.ApiCallBack() {
                    @Override
                    public void onSuccess() {
                        addHistoryView.hideLoading();
                        addHistoryView.showMessage(R.string.history_added_sucessfully);
                        addHistoryView.finish();
                    }

                    @Override
                    public void onFail(String message) {
                        addHistoryView.hideLoading();
                        addHistoryView.showMessage(message);
                    }
                });
    }

    @Override
    public void setDateAndComment(String date, String comment) {
        this.comment = comment;
        this.date = date;
    }

    @Override
    public void setImage(File file) {
        this.file = file;
    }

    @Override
    public boolean isDataValid() {
        if (comment == null || comment.isEmpty()) {
            addHistoryView.setCommentError();
            return false;
        }
        if (date == null || date.isEmpty()) {
            addHistoryView.setDateError();
            return false;
        }

        return true;
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        String date = year + "-" + (month + 1) + "-" + day;
        addHistoryView.setDate(date);
    }

    @Override
    public void showImagePickerDialog(FragmentManager fragmentManager) {
        PickImageDialogFragment pickImageDialogFragment = new PickImageDialogFragment();
        pickImageDialogFragment.setPickImageActions(this);
        pickImageDialogFragment.show(fragmentManager, PickImageDialogFragment.class.getSimpleName());
    }

    @Override
    public void onGalleryClicked() {
        pickImageFromGallery(baseFragment);
    }

    @Override
    public void onCameraClicked() {
        pickImageFromCamera(baseFragment);
    }
}
