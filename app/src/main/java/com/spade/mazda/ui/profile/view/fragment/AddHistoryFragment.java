package com.spade.mazda.ui.profile.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;
import com.spade.mazda.ui.profile.presenter.AddHistoryPresenter;
import com.spade.mazda.ui.profile.presenter.AddHistoryPresenterImpl;
import com.spade.mazda.ui.profile.view.interfaces.AddHistoryView;
import com.spade.mazda.utils.GlideApp;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Ayman Abouzeid on 12/27/17.
 */

public class AddHistoryFragment extends BaseFragment implements AddHistoryView, View.OnClickListener {

    private AddHistoryPresenter addHistoryPresenter;
    private View addHistoryView;
    private CustomEditText dateEditText, commentEditText;
    private MazdaProgressDialog progressDialog;
    private FrameLayout imageLayout;
    private ImageView pickedImage, deleteImageView;
    private RelativeLayout chooseImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addHistoryView = inflater.inflate(R.layout.fragment_add_car_history, container, false);
        initViews();
        return addHistoryView;
    }

    @Override
    protected void initPresenter() {
        addHistoryPresenter = new AddHistoryPresenterImpl(getContext(), this);
        addHistoryPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomButton customButton = addHistoryView.findViewById(R.id.submit_btn);
        chooseImage = addHistoryView.findViewById(R.id.choose_image);
        imageLayout = addHistoryView.findViewById(R.id.image_layout);
        dateEditText = addHistoryView.findViewById(R.id.date_edit_text);
        commentEditText = addHistoryView.findViewById(R.id.comments_edit_text);
        pickedImage = addHistoryView.findViewById(R.id.photo_image_view);
        deleteImageView = addHistoryView.findViewById(R.id.delete_image_view);
        deleteImageView.setOnClickListener(this);
        chooseImage.setOnClickListener(this);
        dateEditText.setOnClickListener(this);
        customButton.setOnClickListener(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int resID) {
        Toast.makeText(getActivity(), getString(resID), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(R.string.loading);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void setCommentError() {
        commentEditText.setError(getString(R.string.please_add_comment));
    }

    @Override
    public void setDateError() {
        dateEditText.setError(getString(R.string.pick_date));
    }

    @Override
    public void setDate(String date) {
        dateEditText.setText(date);
    }

    @Override
    public void finish() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_image:
                addHistoryPresenter.showImagePickerDialog(getChildFragmentManager());
                break;
            case R.id.date_edit_text:
                addHistoryPresenter.showDatePickerDialog(getChildFragmentManager());
                break;
            case R.id.delete_image_view:
                deletePickedImage();
                break;
            case R.id.submit_btn:
                addHistoryPresenter.setDateAndComment(dateEditText.getText().toString(), commentEditText.getText().toString());
                if (addHistoryPresenter.isDataValid()) {
                    addHistoryPresenter.addHistory();
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                addHistoryPresenter.setImage(imageFile);
                showPickedImage(imageFile);
            }
        });
    }

    private void showPickedImage(File imageFile) {
        chooseImage.setVisibility(View.GONE);
        imageLayout.setVisibility(View.VISIBLE);
        Uri uri = Uri.fromFile(imageFile);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        GlideApp.with(getContext()).applyDefaultRequestOptions(requestOptions).load(uri)
                .into(pickedImage);
    }

    private void deletePickedImage() {
        addHistoryPresenter.setImage(null);
        chooseImage.setVisibility(View.VISIBLE);
        imageLayout.setVisibility(View.GONE);
    }
}
