package com.joemerrill.diningreview.model;

import javax.persistence.*;

/**
 * Represents a Restaurant
 */
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Integer zipCode;
    private String phoneNumber;
    private String websiteAddress;

    private Double overallScore;
    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private Double glutenScore;

    //
    // Getters and Setters
    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public Double getPeanutScore() {
        return peanutScore;
    }

    public void setPeanutScore(Double peanutScore) {
        this.peanutScore = peanutScore;
    }

    public Double getEggScore() {
        return eggScore;
    }

    public void setEggScore(Double eggScore) {
        this.eggScore = eggScore;
    }

    public Double getDairyScore() {
        return dairyScore;
    }

    public void setDairyScore(Double dairyScore) {
        this.dairyScore = dairyScore;
    }

    public Double getGlutenScore() {
        return glutenScore;
    }

    public void setGlutenScore(Double glutenScore) {
        this.glutenScore = glutenScore;
    }
}
