package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.*;
import com.joemerrill.diningreview.repository.RestaurantRepository;
import com.joemerrill.diningreview.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(
            ReviewRepository reviewRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public Iterable<Review> getReviewsByStatus(@RequestParam(name = "status") String status) {

        ReviewStatus reviewStatus;
        try {
            reviewStatus = Enum.valueOf(ReviewStatus.class, status.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return reviewRepository.findByStatus(reviewStatus);
    }

    @PutMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adminReviewAction(
            @PathVariable(name = "id") Long reviewId,
            @RequestBody AdminReviewAction adminReviewAction
    ) {

        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        if (reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Review review = reviewOptional.get();

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());

        if (restaurantOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Request cannot be processed. Restaurant not found.");
        }

        if (adminReviewAction.isApprove()) {
            review.setStatus(ReviewStatus.APPROVED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }

        reviewRepository.save(review);
        recalculateRestaurantScores(restaurantOptional.get());
    }

    /**
     * Recalculate Restaurant Review Scores. Usually after approving or canceling an approved review.
     * @param restaurant Restaurant to recalculate
     */
    private void recalculateRestaurantScores(Restaurant restaurant) {

        // Retrieve list of approved reviews.
        List<Review> approvedReviews = reviewRepository.findByRestaurantIdAndStatus(restaurant.getId(), ReviewStatus.APPROVED);

        if (approvedReviews.size() == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Attempt to recalculate restaurant scores. However, no approved reviews exist.");
        }

        int peanutReviewCount = 0;
        double peanutScoreSum = 0.0;
        int eggReviewCount = 0;
        double eggScoreSum = 0.0;
        int dairyReviewCount = 0;
        double dairyScoreSum = 0.0;
        int glutenReviewCount = 0;
        double glutenScoreSum = 0.0;

        for (Review review : approvedReviews) {
            if (!ObjectUtils.isEmpty(review.getPeanutScore())) {
                peanutReviewCount += 1;
                peanutScoreSum += review.getPeanutScore();
            }
            if (!ObjectUtils.isEmpty(review.getEggScore())) {
                eggReviewCount += 1;
                eggScoreSum += review.getEggScore();
            }
            if (!ObjectUtils.isEmpty(review.getDairyScore())) {
                dairyReviewCount += 1;
                dairyScoreSum += review.getDairyScore();
            }
            if (!ObjectUtils.isEmpty(review.getGlutenScore())) {
                glutenReviewCount += 1;
                glutenScoreSum += review.getGlutenScore();
            }
        }

        int overallReviewCount = peanutReviewCount + eggReviewCount + dairyReviewCount + glutenReviewCount;
        double overallScoreSum = peanutScoreSum + eggScoreSum + dairyScoreSum + glutenScoreSum;

        // No need to do further work if there are no reviews. Cannot divide by zero.
        if (overallReviewCount == 0) {
            return;
        }

        restaurant.setOverallScore(overallScoreSum / overallReviewCount);
        if (peanutReviewCount > 0) {
            restaurant.setPeanutScore(peanutScoreSum / peanutReviewCount);
        }
        if (eggReviewCount > 0) {
            restaurant.setEggScore(eggScoreSum / eggReviewCount);
        }
        if (dairyReviewCount > 0) {
            restaurant.setDairyScore(dairyScoreSum / dairyReviewCount);
        }
        if (glutenReviewCount > 0) {
            restaurant.setGlutenScore(glutenScoreSum / glutenReviewCount);
        }

        restaurantRepository.save(restaurant);
    }
}
