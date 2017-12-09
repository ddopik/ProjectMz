package com.spade.mazda.ui.general.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class PickImageDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_image_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init(view);
        return view;
    }

    private void init(View view) {
        CustomTextView galleryText = view.findViewById(R.id.gallery_text);
        CustomTextView cameraText = view.findViewById(R.id.camera_text);
        galleryText.setOnClickListener(btn -> {
            pickImageActions.onGalleryClicked();
            dismiss();
        });
        cameraText.setOnClickListener(btn -> {
            pickImageActions.onCameraClicked();
            dismiss();
        });

    }

    private PickImageActions pickImageActions;

    public void setPickImageActions(PickImageActions pickImageActions) {
        this.pickImageActions = pickImageActions;
    }

    public interface PickImageActions {
        void onGalleryClicked();

        void onCameraClicked();
    }
}
