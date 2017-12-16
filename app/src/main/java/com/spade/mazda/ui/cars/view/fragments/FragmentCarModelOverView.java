package com.spade.mazda.ui.cars.view.fragments;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.CarDetailsData;
import com.spade.mazda.ui.cars.model.Gallery;
import com.spade.mazda.ui.cars.view.activity.BookCarActivity;
import com.spade.mazda.ui.cars.view.adapter.CarActionsAdapter;
import com.spade.mazda.ui.cars.view.adapter.CarImagesAdapter;
import com.spade.mazda.ui.general.view.LoginDialogFragment;
import com.spade.mazda.ui.services.view.activities.FinanceActivity;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class FragmentCarModelOverView extends BaseFragment implements CarActionsAdapter.OnActionClicked {

    private View carOverView;
    private List<Gallery> carImages = new ArrayList<>();
    private CarImagesAdapter carImagesAdapter;
//    private OnBookClicked onBookClicked;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carOverView = inflater.inflate(R.layout.fragment_car_overview, container, false);
        initViews();
        return carOverView;
    }

    @Override
    protected void initPresenter() {

    }

    @SuppressLint("Recycle")
    @Override
    protected void initViews() {
        CustomRecyclerView galleryRecyclerView = carOverView.findViewById(R.id.car_images_recycler_view);
        CustomRecyclerView carActionsRecyclerView = carOverView.findViewById(R.id.actions_recycler_view);
        CustomTextView bookNow = carOverView.findViewById(R.id.book_now);
        carActionsRecyclerView.setNestedScrollingEnabled(false);
        carImagesAdapter = new CarImagesAdapter(getContext(), carImages);
        galleryRecyclerView.setAdapter(carImagesAdapter);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.action_images);
        CarActionsAdapter carActionsAdapter = new CarActionsAdapter(getContext(), typedArray);
        carActionsAdapter.setOnActionClicked(this);
        carActionsRecyclerView.setAdapter(carActionsAdapter);

        bookNow.setOnClickListener(view -> navigateToBook());
    }

    @Override
    public void onActionClicked(int position) {
        switch (position) {
            case 0:
                startActivity(FinanceActivity.getLaunchIntent(getContext()));
                break;
        }
    }

    public void updateUI(CarDetailsData carDetailsData) {
        this.carImages.clear();
        this.carImages.addAll(carDetailsData.getGallery());
        this.carImagesAdapter.notifyDataSetChanged();
    }

    private void navigateToBook() {
        if (PrefUtils.isLoggedIn(getContext())) {
            startActivity(BookCarActivity.getLaunchIntent(getContext()));
        } else {
            showLoginDialog();
        }
    }

    private void showLoginDialog() {
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(getChildFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }

//    public void setOnBookClicked(OnBookClicked onBookClicked) {
//        this.onBookClicked = onBookClicked;
//    }
//
//    public interface OnBookClicked {
//        void onBookNowClicked();
//    }
}
