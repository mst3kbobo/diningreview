package com.joemerrill.diningreview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a User
 */
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String displayName;
    private String city;
    private String state;
    private Integer zipCode;

    private Boolean hasPeanutInterest;
    private Boolean hasEggInterest;
    private Boolean hasDairyInterest;
    private Boolean hasGlutenInterest;
}
