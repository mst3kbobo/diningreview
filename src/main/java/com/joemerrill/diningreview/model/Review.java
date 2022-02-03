package com.joemerrill.diningreview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a Dining Review
 */
@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "SUBMITTED_BY")
    @Getter @Setter
    private String submittedBy;

    @Column(name = "RESTAURANT_ID")
    @Getter @Setter
    private Long restaurantId;

    @Column(name = "PEANUT_SCORE")
    @Getter @Setter
    private Integer peanutScore;

    @Column(name = "EGG_SCORE")
    @Getter @Setter
    private Integer eggScore;

    @Column(name = "DAIRY_SCORE")
    @Getter @Setter
    private Integer dairyScore;

    @Column(name = "COMMENT")
    @Getter @Setter
    private String comment;
}
