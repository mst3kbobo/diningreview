package com.joemerrill.diningreview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a Dining Review
 */
@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;
    private String submittedBy;
    private String review;
    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private Integer glutenScore;
}
