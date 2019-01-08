package com.spade.mazda.ui.authentication.presenter;

import com.spade.mazda.ui.authentication.view.interfaces.RegistrationSecondStepView;

public interface RegistrationPresenterStepTwoPresenter {
    void setView(RegistrationSecondStepView registrationSecondStepView);

    void register(String appLang, String nameString, String emailString, String passwordString, String mobileNumberString, String birthDateString);
}
