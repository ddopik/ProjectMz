package com.spade.mazda.ui.services.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.services.model.Place360;
import com.spade.mazda.ui.services.presenter.Places360Presenter;
import com.spade.mazda.ui.services.presenter.Places360PresenterImpl;
import com.spade.mazda.ui.services.view.adapters.Places360Adapter;
import com.spade.mazda.ui.services.view.interfaces.Places360View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public class Places360Fragment extends BaseFragment implements Places360View {

    private Places360Presenter places360Presenter;
    private View place360View;
    private ProgressBar progressBar;
    private Places360Adapter places360Adapter;
    private List<Place360> place360List;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        place360View = inflater.inflate(R.layout.fragment_360, container, false);
        initViews();
        return place360View;
    }

    @Override
    protected void initPresenter() {
        places360Presenter = new Places360PresenterImpl(getContext());
        places360Presenter.setView(this);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {
        RecyclerView recyclerView = place360View.findViewById(R.id.places_360_recycler);
        ImageView transparentImage = place360View.findViewById(R.id.transparent_image);
        ScrollView mainScroll = place360View.findViewById(R.id.main_scroll);
        progressBar = place360View.findViewById(R.id.progress_bar);
        recyclerView.setNestedScrollingEnabled(false);

        place360List = new ArrayList<>();
        places360Adapter = new Places360Adapter(getContext(), place360List);
        recyclerView.setAdapter(places360Adapter);
        places360Presenter.getPlaces();


        transparentImage.setOnTouchListener((View v, MotionEvent event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mainScroll.requestDisallowInterceptTouchEvent(true);
                    return false;

                case MotionEvent.ACTION_UP:
                    mainScroll.requestDisallowInterceptTouchEvent(false);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    mainScroll.requestDisallowInterceptTouchEvent(true);
                    return false;
                default:
                    return true;
            }
        });
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showPlaces(List<Place360> place360List) {
        this.place360List.clear();
        this.place360List.addAll(place360List);
        this.places360Adapter.notifyDataSetChanged();
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

}
