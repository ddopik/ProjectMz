package com.spade.mazda.ui.services.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.home.model.Offer;
import com.spade.mazda.ui.home.view.adapters.OffersPagerAdapter;
import com.spade.mazda.ui.services.model.Program;
import com.spade.mazda.ui.services.presenter.FinancePresenter;
import com.spade.mazda.ui.services.presenter.FinancePresenterImpl;
import com.spade.mazda.ui.services.view.adapters.ProgramsAdapter;
import com.spade.mazda.ui.services.view.interfaces.FinanceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/8/17.
 */

public class FinanceFragment extends BaseFragment implements FinanceView {

    private FinancePresenter financePresenter;
    private View driveFinanceView;
    private ProgressBar programsProgressBar, offersProgressBar;
    private ProgramsAdapter programsAdapter;
    private OffersPagerAdapter offersPagerAdapter;
    private List<Offer> offers = new ArrayList<>();
    private List<Program> programs = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        driveFinanceView = inflater.inflate(R.layout.fragment_finance, container, false);
        initViews();
        return driveFinanceView;
    }

    @Override
    protected void initPresenter() {
        financePresenter = new FinancePresenterImpl(getContext());
        financePresenter.setView(this);
    }

    @Override
    protected void initViews() {
//        ViewPager offersViewPager = driveFinanceView.findViewById(R.id.offers_pager);
//        TabLayout tabLayout = driveFinanceView.findViewById(R.id.tab_layout);
        CustomRecyclerView programsRecyclerView = driveFinanceView.findViewById(R.id.programs_recycler_view);
        programsProgressBar = driveFinanceView.findViewById(R.id.programs_progress_bar);
        offersProgressBar = driveFinanceView.findViewById(R.id.offers_progress_bar);
//        offersPagerAdapter = new OffersPagerAdapter(getContext(), offers);
        programsAdapter = new ProgramsAdapter(getContext(), programs);

//        offersViewPager.setAdapter(offersPagerAdapter);
//        tabLayout.setupWithViewPager(offersViewPager, true);

        programsRecyclerView.setAdapter(programsAdapter);

//        financePresenter.getOffers();
        financePresenter.getPrograms();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

    }

    @Override
    public void showLoading() {
        offersProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        offersProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setError(EditText editText, int resId) {

    }

    @Override
    public void showProgramsLoading() {
        programsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgramsLoading() {
        programsProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showOffers(List<Offer> offers) {
//        this.offers.clear();
//        this.offers.addAll(offers);
//        offersPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPrograms(List<Program> programs) {
        this.programs.clear();
        this.programs.addAll(programs);
        programsAdapter.notifyDataSetChanged();
    }
}
