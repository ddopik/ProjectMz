package com.spade.mazda.ui.mazda_club.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;
import com.spade.mazda.ui.mazda_club.presenter.MazdaClubPresenter;
import com.spade.mazda.ui.mazda_club.presenter.MazdaClubPresenterImpl;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class MazdaClubFragment extends BaseFragment implements MazdaClubView {

    private MazdaClubPresenter mazdaClubPresenter;
    private View mazdaClubView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mazdaClubView = inflater.inflate(R.layout.fragment_mazda_club, container, false);
        initViews();
        return mazdaClubView;
    }

    @Override
    protected void initPresenter() {
        mazdaClubPresenter = new MazdaClubPresenterImpl(getContext());
        mazdaClubPresenter.setView(this);
    }

    @Override
    protected void initViews() {
        ViewPager tiersViewPager = mazdaClubView.findViewById(R.id.tiers_view_pager);

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showMazdaClubsTiers(MazdaClubData mazdaClubData) {

    }
}
