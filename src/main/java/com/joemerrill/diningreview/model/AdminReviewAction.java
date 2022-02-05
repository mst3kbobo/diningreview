package com.joemerrill.diningreview.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents whether an admin approves a user review.
 */
@Getter
@Setter
public class AdminReviewAction {
    private boolean approve;
}
