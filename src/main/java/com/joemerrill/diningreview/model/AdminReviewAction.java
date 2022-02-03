package com.joemerrill.diningreview.model;

/**
 * Represents whether an admin accepts a user review.
 */
public class AdminReviewAction {

    private boolean acceptReview;

    public boolean isAcceptReview() {
        return acceptReview;
    }

    public void setAcceptReview(boolean acceptReview) {
        this.acceptReview = acceptReview;
    }
}
