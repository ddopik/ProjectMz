package com.spade.mazda.ui.authentication.view.interfaces;

public interface RegistrationSecondStepView {
    void showLoading();

    void navigateToLogin();

    void navigateToNextStep();

    void showMessage(String message);

    void hideLoading();

    void navigateToActivate(String email);
}
