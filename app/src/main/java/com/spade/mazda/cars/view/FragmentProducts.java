package com.spade.mazda.cars.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.cars.model.CarModel;
import com.spade.mazda.cars.presenter.ProductsPresenter;
import com.spade.mazda.cars.presenter.ProductsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/2/17.
 */

public class FragmentProducts extends BaseFragment implements ProductsView {
    private View view;
    private ProgressBar progressBar;
    private ProductsPresenter productsPresenter;
    private List<CarModel> carModelList;
    private ProductsAdapter productsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listing, container,false);
        initViews();
        return view;
    }

    @Override
    protected void initPresenter() {
        productsPresenter = new ProductsPresenterImpl(getContext());
        productsPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomRecyclerView carsRecyclerView = view.findViewById(R.id.items_recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        carModelList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(carModelList, getContext());
        carsRecyclerView.setAdapter(productsAdapter);
        productsPresenter.getProducts("");
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
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProducts(List<CarModel> carModels) {
        this.carModelList.clear();
        this.carModelList.addAll(carModels);
        this.productsAdapter.notifyDataSetChanged();
    }
}
