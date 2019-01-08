
package com.spade.mazda.ui.authentication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("national_id")
    @Expose
    private String nationalId;
    @SerializedName("national_id_front_image")
    @Expose
    private Integer nationalIdFrontImage;
    @SerializedName("national_id_back_image")
    @Expose
    private Integer nationalIdBackImage;
    @SerializedName("car_model")
    @Expose
    private Integer carModel;
    @SerializedName("car_year")
    @Expose
    private Integer carYear;
    @SerializedName("car_trim")
    @Expose
    private Integer carTrim;
    @SerializedName("car_color")
    @Expose
    private Integer carColor;
    @SerializedName("motor")
    @Expose
    private String motor;
    @SerializedName("chassis")
    @Expose
    private String chassis;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("forget_code")
    @Expose
    private String forgetCode;
    @SerializedName("confirmation_code")
    @Expose
    private String confirmationCode;
    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("is_verified")
    @Expose
    private Integer isVerified;
    @SerializedName("is_blocked")
    @Expose
    private Integer isBlocked;

    @SerializedName("tier")
    @Expose
    private String tier;


    public final static Parcelable.Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @SuppressWarnings({"unchecked"})
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        public UserModel[] newArray(int size) {
            return (new UserModel[size]);
        }

    };

    protected UserModel(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.birthDate = ((String) in.readValue((String.class.getClassLoader())));
        this.nationalId = ((String) in.readValue((String.class.getClassLoader())));
        this.nationalIdFrontImage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nationalIdBackImage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.carModel = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.carYear = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.carTrim = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.carColor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.chassis = ((String) in.readValue((String.class.getClassLoader())));
        this.motor = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.forgetCode = ((String) in.readValue((String.class.getClassLoader())));
        this.confirmationCode = ((String) in.readValue((String.class.getClassLoader())));
        this.isAdmin = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.isVerified = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isBlocked = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tier = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(mobileNumber);
        dest.writeValue(birthDate);
        dest.writeValue(nationalId);
        dest.writeValue(nationalIdFrontImage);
        dest.writeValue(nationalIdBackImage);
        dest.writeValue(carModel);
        dest.writeValue(carYear);
        dest.writeValue(carTrim);
        dest.writeValue(carColor);
        dest.writeValue(chassis);
        dest.writeValue(motor);
        dest.writeValue(image);
        dest.writeValue(forgetCode);
        dest.writeValue(confirmationCode);
        dest.writeValue(isAdmin);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(isVerified);
        dest.writeValue(isBlocked);
        dest.writeValue(tier);
    }

    public int describeContents() {
        return 0;
    }

}
