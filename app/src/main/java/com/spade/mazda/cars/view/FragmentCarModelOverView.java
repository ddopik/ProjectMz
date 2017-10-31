package com.spade.mazda.cars.view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.settings.view.CustomRecyclerView;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class FragmentCarModelOverView extends BaseFragment implements CarActionsAdapter.OnActionClicked {

    private View carOverView;

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

    @Override
    protected void initViews() {
        CustomRecyclerView galleryRecyclerView = carOverView.findViewById(R.id.car_images_recycler_view);
        CustomRecyclerView carActionsRecyclerView = carOverView.findViewById(R.id.actions_recycler_view);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.action_images);
        CarActionsAdapter carActionsAdapter = new CarActionsAdapter(getContext(), typedArray);
        carActionsAdapter.setOnActionClicked(this);
        carActionsRecyclerView.setAdapter(carActionsAdapter);
        typedArray.recycle();
    }

    @Override
    public void onActionClicked(int position) {

    }
}
