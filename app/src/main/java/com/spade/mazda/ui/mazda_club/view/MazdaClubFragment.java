package com.spade.mazda.ui.mazda_club.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.FlipHorizontalTransformer;
import com.eftimoff.viewpagertransformers.RotateDownTransformer;
import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.mazda_club.model.MazdaClubData;
import com.spade.mazda.ui.mazda_club.presenter.MazdaClubPresenter;
import com.spade.mazda.ui.mazda_club.presenter.MazdaClubPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/19/17.
 */

public class MazdaClubFragment extends BaseFragment implements MazdaClubView {

    private MazdaClubPresenter mazdaClubPresenter;
    private View mazdaClubView;
    private ProgressBar progressBar;
//    private MazdaClubPagerAdapter mazdaClubPagerAdapter;
    private MazdaClubAdapter clubAdapter;
    private List<MazdaClubData> mazdaClubDataList = new ArrayList<>();

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
//        ViewPager tiersViewPager = mazdaClubView.findViewById(R.id.tiers_view_pager);
//        tiersViewPager.setPageTransformer(true, new DepthPageTransformer());
//        tiersViewPager.setClipToPadding(false);
//        tiersViewPager.setPadding(48, 0, 48, 0);
//        tiersViewPager.setPageMargin(48);
        CustomRecyclerView tiersRecycler = mazdaClubView.findViewById(R.id.tiers_recycler);
        progressBar = mazdaClubView.findViewById(R.id.progress_bar);
        clubAdapter = new MazdaClubAdapter(getContext(), mazdaClubDataList);
//        mazdaClubPagerAdapter = new MazdaClubPagerAdapter(getContext(), mazdaClubDataList);
//        tiersViewPager.setAdapter(mazdaClubPagerAdapter);

        tiersRecycler.setAdapter(clubAdapter);
        mazdaClubPresenter.getMazdaClubTiers();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int resID) {
        Toast.makeText(getContext(), getString(resID), Toast.LENGTH_LONG).show();
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
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showMazdaClubsTiers(List<MazdaClubData> mazdaClubDataList) {
        this.mazdaClubDataList.clear();
        this.mazdaClubDataList.addAll(mazdaClubDataList);
        clubAdapter.notifyDataSetChanged();
//        mazdaClubPagerAdapter.notifyDataSetChanged();
    }
}
