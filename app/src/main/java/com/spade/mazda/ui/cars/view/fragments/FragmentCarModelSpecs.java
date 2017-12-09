package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.model.Category;
import com.spade.mazda.ui.cars.view.adapter.CarSpecsCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 10/30/17.
 */

public class FragmentCarModelSpecs extends BaseFragment {

    private View specsView;
    private List<Category> specsCategoryList = new ArrayList<>();
    private CarSpecsCategoriesAdapter carSpecsCategoriesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        specsView = inflater.inflate(R.layout.fragment_listing, container, false);
        initViews();
        return specsView;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        CustomRecyclerView specsCategoriesRecycler = specsView.findViewById(R.id.items_recycler_view);
        carSpecsCategoriesAdapter = new CarSpecsCategoriesAdapter(getContext(), specsCategoryList);
        specsCategoriesRecycler.setAdapter(carSpecsCategoriesAdapter);
    }

    public void updateSpecs(List<Category> specsCategoryList) {
        this.specsCategoryList.clear();
        this.specsCategoryList.addAll(specsCategoryList);
        carSpecsCategoriesAdapter.notifyDataSetChanged();
    }
}
