package com.spade.mazda.ui.profile.presenter;

import android.support.v4.app.FragmentManager;

import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.base.BasePresenter;
import com.spade.mazda.ui.profile.view.interfaces.AddHistoryView;

import java.io.File;

/**
 * Created by Ayman Abouzeid on 12/27/17.
 */

public interface AddHistoryPresenter extends BasePresenter<AddHistoryView> {
    void showDatePickerDialog(FragmentManager fragmentManager);

    void pickImageFromCamera(BaseFragment baseFragment);

    void pickImageFromGallery(BaseFragment baseFragment);

    void addHistory();

    void setDateAndComment(String date, String comment);

    void setImage(File file);

    boolean isDataValid();

    void showImagePickerDialog(FragmentManager fragmentManager);
}
