package com.spade.mazda.ui.profile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayman Abouzeid on 12/28/17.
 */

public class History {

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;
    @SerializedName("groupName")
    @Expose
    private String groupName;
    @SerializedName("incidentDate")
    @Expose
    private String incidentDate;
    @SerializedName("incidentNumber")
    @Expose
    private String incidentNumber;
    @SerializedName("installDate")
    @Expose
    private String installDate;
    @SerializedName("jobType")
    @Expose
    private String jobType;
    @SerializedName("km")
    @Expose
    private String km;
    @SerializedName("netAmount")
    @Expose
    private String netAmount;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("serviceAdvisor")
    @Expose
    private String serviceAdvisor;
    @SerializedName("taskStatus")
    @Expose
    private String taskStatus;
    @SerializedName("type")
    @Expose
    private String type;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
