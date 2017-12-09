package com.spade.mazda.ui.authentication.model;

import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ayman Abouzeid on 6/13/17.
 */

public class User extends RealmObject {

    @PrimaryKey
    private Integer userId;
    private String userToken;
    private String userEmail;
    private String userName;
    private String userPhone;
    private String birthDate;
    private String nationalId;
    private Integer nationalIdFrontImage;
    private Integer nationalIdBackImage;
    private Integer carModel;
    private Integer carYear;
    private Integer carTrim;
    private Integer carColor;
    private String motor;
    private String chassis;
    private String image;
    private String forgetCode;
    private String confirmationCode;
    private Integer isAdmin;
    private String createdAt;
    private String updatedAt;
    private Integer isVerified;
    private Integer isBlocked;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@Nullable String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(@Nullable String userName) {
        this.userName = userName;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Integer getNationalIdFrontImage() {
        return nationalIdFrontImage;
    }

    public void setNationalIdFrontImage(Integer nationalIdFrontImage) {
        this.nationalIdFrontImage = nationalIdFrontImage;
    }

    public Integer getNationalIdBackImage() {
        return nationalIdBackImage;
    }

    public void setNationalIdBackImage(Integer nationalIdBackImage) {
        this.nationalIdBackImage = nationalIdBackImage;
    }

    public Integer getCarModel() {
        return carModel;
    }

    public void setCarModel(Integer carModel) {
        this.carModel = carModel;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Integer getCarTrim() {
        return carTrim;
    }

    public void setCarTrim(Integer carTrim) {
        this.carTrim = carTrim;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(String forgetCode) {
        this.forgetCode = forgetCode;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public Integer getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Integer isBlocked) {
        this.isBlocked = isBlocked;
    }
}
