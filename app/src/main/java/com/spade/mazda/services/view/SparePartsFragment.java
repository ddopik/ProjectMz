package com.spade.mazda.services.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.services.model.SparePartCategory;
import com.spade.mazda.services.presenter.SparePartsPresenter;
import com.spade.mazda.services.presenter.SparePartsPresenterImpl;
import com.spade.mazda.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/8/17.
 */

public class SparePartsFragment extends BaseFragment implements SparePartsView {

    private SparePartsPresenter sparePartsPresenter;
    private View sparePartsView;
    private ProgressBar progressBar;
    private SparePartsCategoriesAdapter sparePartsCategoriesAdapter;
    private List<SparePartCategory> sparePartCategories = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sparePartsView = inflater.inflate(R.layout.fragment_spare_parts, container, false);
        initViews();
        return sparePartsView;
    }

    @Override
    protected void initPresenter() {
        sparePartsPresenter = new SparePartsPresenterImpl();
        sparePartsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomRecyclerView sparePartsRecycler = sparePartsView.findViewById(R.id.spare_parts_recycler_view);
        TextView requestSparePartBtn = sparePartsView.findViewById(R.id.request_spare_part_button);
        sparePartsCategoriesAdapter = new SparePartsCategoriesAdapter(getContext(), sparePartCategories);
        progressBar = sparePartsView.findViewById(R.id.progress_bar);
        sparePartsRecycler.setAdapter(sparePartsCategoriesAdapter);
        sparePartsPresenter.getSpareParts(PrefUtils.getAppLang(getContext()));
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
    public void showSparParts(List<SparePartCategory> sparePartCategories) {
        this.sparePartCategories.clear();
        this.sparePartCategories.addAll(sparePartCategories);
        sparePartsCategoriesAdapter.notifyDataSetChanged();
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
