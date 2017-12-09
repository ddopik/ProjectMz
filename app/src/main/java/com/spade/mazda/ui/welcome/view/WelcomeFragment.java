package com.spade.mazda.ui.welcome.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.welcome.model.IntroSlide;
import com.spade.mazda.ui.welcome.presenter.WelcomePresenter;
import com.spade.mazda.ui.welcome.presenter.WelcomePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/22/17.
 */

public class WelcomeFragment extends BaseFragment implements WelcomeView {

    private WelcomePresenter welcomePresenter;
    private View welcomeView;
    private SlidesPagerAdapter slidesPagerAdapter;
    private List<IntroSlide> introSlides = new ArrayList<>();
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        welcomeView = inflater.inflate(R.layout.fragment_welcome, container, false);
        initViews();
        return welcomeView;
    }

    @Override
    protected void initPresenter() {
        welcomePresenter = new WelcomePresenterImpl(getContext());
        welcomePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        progressBar = welcomeView.findViewById(R.id.progress_bar);
        TextView registerText = welcomeView.findViewById(R.id.register_text);
        TextView loginText = welcomeView.findViewById(R.id.login_text);
        TextView loginAsGuestText = welcomeView.findViewById(R.id.guest_text);
        ViewPager viewPager = welcomeView.findViewById(R.id.slides_pager);
        TabLayout tabLayout = welcomeView.findViewById(R.id.tab_layout);

        loginText.setOnClickListener(view -> welcomePresenter.navigateToLogin());
        loginAsGuestText.setOnClickListener(view -> welcomePresenter.navigateToMainScreen());
        registerText.setOnClickListener(view -> welcomePresenter.navigateToRegister());

        slidesPagerAdapter = new SlidesPagerAdapter(getContext(), introSlides);
        viewPager.setAdapter(slidesPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
        welcomePresenter.getIntroSlides();
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
    public void finish() {
        getActivity().finish();
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
    public void showSlides(List<IntroSlide> introSlides) {
        this.introSlides.clear();
        this.introSlides.addAll(introSlides);
        slidesPagerAdapter.notifyDataSetChanged();
    }
}
