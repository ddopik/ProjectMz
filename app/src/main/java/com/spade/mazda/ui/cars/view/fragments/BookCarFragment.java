package com.spade.mazda.ui.cars.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spade.mazda.CustomViews.CustomButton;
import com.spade.mazda.CustomViews.CustomEditText;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.presenter.BookCarPresenterImpl;
import com.spade.mazda.ui.cars.presenter.interfaces.BookCarPresenter;
import com.spade.mazda.ui.cars.view.interfaces.BookCarView;
import com.spade.mazda.ui.find_us.model.Branch;
import com.spade.mazda.ui.general.view.dialog.MazdaProgressDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/14/17.
 */

public class BookCarFragment extends BaseFragment implements BookCarView, View.OnClickListener {

    private CustomEditText nameEditText, mobileNumberEditText,
            carModelEditText, carYearsEditText, carTrimsEditText, showRoomEditText;

    private List<Branch> showroomList = new ArrayList<>();

    private BookCarPresenter bookCarPresenter;
    private View bookCarView;
    private ProgressBar progressBar;
    private MazdaProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookCarView = inflater.inflate(R.layout.fragment_book_car, container, false);
        return bookCarView;
    }

    @Override
    protected void initPresenter() {
        bookCarPresenter = new BookCarPresenterImpl(getContext());
        bookCarPresenter.setView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected void initViews() {
        CustomButton customButton = bookCarView.findViewById(R.id.book_btn);

        progressBar = bookCarView.findViewById(R.id.progress_bar);
        nameEditText = bookCarView.findViewById(R.id.name_edit_text);
        mobileNumberEditText = bookCarView.findViewById(R.id.mobile_edit_text);
        carModelEditText = bookCarView.findViewById(R.id.car_model_edit_text);
        carYearsEditText = bookCarView.findViewById(R.id.car_year_edit_text);
        carTrimsEditText = bookCarView.findViewById(R.id.car_trim_edit_text);
        showRoomEditText = bookCarView.findViewById(R.id.show_room_edit_text);

        bookCarPresenter.getUser();
        bookCarPresenter.getShowRooms();

        customButton.setOnClickListener(this);
        carModelEditText.setOnClickListener(this);
        carYearsEditText.setOnClickListener(this);
        carTrimsEditText.setOnClickListener(this);
        showRoomEditText.setOnClickListener(this);


        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bookCarPresenter.setName(editable.toString());
            }
        });

        mobileNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bookCarPresenter.setMobileNumber(editable.toString());
            }
        });
//        modelId = getArguments().getInt(CarDetailsFragment.EXTRA_CAR_ID);
//        yearPosition = getArguments().getInt(CarDetailsFragment.EXTRA_YEAR_POSITION);
//        trimPosition = getArguments().getInt(CarDetailsFragment.EXTRA_TRIM_POSITION);
//

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
    public void showNearestShowRooms(List<Branch> branches) {
        this.showroomList.clear();
        this.showroomList.addAll(branches);
    }

    @Override
    public void showName(String userName) {
        nameEditText.setText(userName);
    }

    @Override
    public void showMobileNumber(String mobileNumber) {
        mobileNumberEditText.setText(mobileNumber);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null)
            progressDialog = new MazdaProgressDialog();
        progressDialog.setLoadingTextResID(R.string.loading);
        progressDialog.setCancelable(false);
        progressDialog.show(getChildFragmentManager(), MazdaProgressDialog.class.getSimpleName());
    }


    @Override
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }


    @Override
    public void showConfirmationDialog() {
        showConfirmationDialog(R.string.thanks_for_reserving);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void setCarModel(String carModel) {
        carModelEditText.setText(carModel);
    }

    @Override
    public void setCarYear(String carYear) {
        carYearsEditText.setText(carYear);
    }

    @Override
    public void setCarTrim(String carTrim) {
        carTrimsEditText.setText(carTrim);
    }

    @Override
    public void setBranchName(String branchName) {
        showRoomEditText.setText(branchName);
    }

    @Override
    public void setCarModelError() {
        carModelEditText.setError(getString(R.string.please_choose_car_model));
    }

    @Override
    public void setCarYearError() {
        carYearsEditText.setError(getString(R.string.please_choose_car_year));

    }

    @Override
    public void setCarTrimError() {
        carTrimsEditText.setError(getString(R.string.please_choose_car_trim));

    }

    @Override
    public void setNameError() {
        nameEditText.setError(getString(R.string.enter_user_name));
    }

    @Override
    public void setMobileNumberError() {
        mobileNumberEditText.setError(getString(R.string.enter_phone_number));
    }

    @Override
    public void setBranchError() {
        showRoomEditText.setError(getString(R.string.please_choose_nearest_showroom));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_btn:
                if (bookCarPresenter.isDataValid()) {
                    bookCarPresenter.bookCar();
                }
                break;
            case R.id.car_model_edit_text:
                bookCarPresenter.showCarModelsDialog(getChildFragmentManager());
                break;
            case R.id.car_year_edit_text:
                bookCarPresenter.showCarYearsDialog(getChildFragmentManager());
                break;
            case R.id.car_trim_edit_text:
                bookCarPresenter.showCarTrimsDialog(getChildFragmentManager());
                break;
            case R.id.show_room_edit_text:
                bookCarPresenter.showBranches(getChildFragmentManager(), showroomList);
                break;
        }

    }

}
