package com.joemerrill.diningreview.model;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OVERALL_RATING")
    private Double overallRating;

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

    public Double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Double overallRating) {
        this.overallRating = overallRating;
    }
}
