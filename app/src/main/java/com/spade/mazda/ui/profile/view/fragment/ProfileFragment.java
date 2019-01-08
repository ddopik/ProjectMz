package com.spade.mazda.ui.profile.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.spade.mazda.CustomViews.CustomRecyclerView;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.CarModel;
import com.spade.mazda.ui.profile.model.History;
import com.spade.mazda.ui.profile.presenter.ProfilePresenter;
import com.spade.mazda.ui.profile.presenter.ProfilePresenterImpl;
import com.spade.mazda.ui.profile.view.HistoryAdapter;
import com.spade.mazda.ui.profile.view.activity.AddHistoryActivity;
import com.spade.mazda.ui.profile.view.activity.EditProfileActivity;
import com.spade.mazda.ui.profile.view.interfaces.ProfileView;
import com.spade.mazda.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/20/17.
 */

public class ProfileFragment extends BaseFragment implements ProfileView {

    private static final int EDIT_REQUEST_CODE = 200;
    private static final int HISTORY_REQUEST_CODE = 300;

    private ProfilePresenter profilePresenter;
    private View profileView;
    private CustomTextView userName, userCarModel, tierName,
            tierExpiration, carModelName, carChassis, carMotor, carYear, carColor;
    private ImageView userImage, carImage;
    private ProgressBar progressBar;
    private List<History> historyList = new ArrayList<>();
    private HistoryAdapter historyAdapter;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        return profileView;
    }

    @Override
    protected void initPresenter() {
        profilePresenter = new ProfilePresenterImpl(getContext());
        profilePresenter.setView(this);
    }

    @Override
    protected void initViews() {
        CustomRecyclerView historyRecycler = profileView.findViewById(R.id.car_history_recycler);
        CustomTextView editProfileBtn = profileView.findViewById(R.id.edit_profile_btn);
        ImageView addHistory = profileView.findViewById(R.id.add_history);
        userName = profileView.findViewById(R.id.user_name);
        userCarModel = profileView.findViewById(R.id.user_car_model);
        tierName = profileView.findViewById(R.id.tier_name);
        tierExpiration = profileView.findViewById(R.id.expiration_date);
        carModelName = profileView.findViewById(R.id.model_title);
        carChassis = profileView.findViewById(R.id.chassis);
        carMotor = profileView.findViewById(R.id.motor);
        carYear = profileView.findViewById(R.id.model_year);
        carColor = profileView.findViewById(R.id.model_color);
        userImage = profileView.findViewById(R.id.user_image);
        carImage = profileView.findViewById(R.id.model_image);
        progressBar = profileView.findViewById(R.id.progress_bar);

        historyRecycler.setNestedScrollingEnabled(false);
        historyAdapter = new HistoryAdapter(getContext(), historyList);
        historyRecycler.setAdapter(historyAdapter);

        profilePresenter.getUserData();
        profilePresenter.getUserCarHistory();


        addHistory.setOnClickListener(view -> startActivityForResult(AddHistoryActivity.getLaunchIntent(getContext()), HISTORY_REQUEST_CODE));
        editProfileBtn.setOnClickListener(view -> startActivityForResult(EditProfileActivity.getLaunchIntent(getContext()), EDIT_REQUEST_CODE));
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resID) {

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
    public void updateUI(User user) {
        userName.setText(user.getUserName());
        carChassis.setText(String.format(getString(R.string.chassis_number), user.getChassis()));
        carMotor.setText(String.format(getString(R.string.motor_number), user.getMotor()));
        if(user.getCarModel() !=null)
        profilePresenter.getCarDetails(user.getCarModel());
    }

    @Override
    public void setCarModel(CarModel carModel) {
        carModelName.setText(carModel.getCarModelName());
        userCarModel.setText(carModel.getCarModelName());
        GlideApp.with(getContext()).load(carModel.getCarModelImage()).into(carImage);
    }

    @Override
    public void setCarColor(String carColor) {
        this.carColor.setText(String.format(getString(R.string.color), carColor));
    }

    @Override
    public void setCarYear(String carYear) {
        this.carYear.setText(String.format(getString(R.string.model), carYear));
    }

    @Override
    public void showHistory(List<History> historyList) {
        if (historyList != null) {
            this.historyList.clear();
            this.historyList.addAll(historyList);
        }
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST_CODE) {
            profilePresenter.getUserData();
        }

        if (requestCode == HISTORY_REQUEST_CODE) {
            profilePresenter.getUserCarHistory();
        }
    }
}
