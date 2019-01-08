
package com.spade.mazda.ui.mazda_club.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlueTier {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("package id")
    @Expose
    private Integer packageId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("labor cost")
    @Expose
    private String laborCost;
    @SerializedName("spare parts")
    @Expose
    private String spareParts;
    @SerializedName("fixology")
    @Expose
    private String fixology;
    @SerializedName("fabrika")
    @Expose
    private String fabrika;
    @SerializedName("from total loan")
    @Expose
    private String fromTotalLoan;
    @SerializedName("labor coast")
    @Expose
    private String laborCoast;
    @SerializedName("products")
    @Expose
    private String products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(String laborCost) {
        this.laborCost = laborCost;
    }

    public String getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(String spareParts) {
        this.spareParts = spareParts;
    }

    public String getFixology() {
        return fixology;
    }

    public void setFixology(String fixology) {
        this.fixology = fixology;
    }

    public String getFabrika() {
        return fabrika;
    }

    public void setFabrika(String fabrika) {
        this.fabrika = fabrika;
    }

    public String getFromTotalLoan() {
        return fromTotalLoan;
    }

    public void setFromTotalLoan(String fromTotalLoan) {
        this.fromTotalLoan = fromTotalLoan;
    }

    public String getLaborCoast() {
        return laborCoast;
    }

    public void setLaborCoast(String laborCoast) {
        this.laborCoast = laborCoast;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

}
