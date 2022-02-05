package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.AdminReviewAction;
import com.joemerrill.diningreview.model.Restaurant;
import com.joemerrill.diningreview.model.Review;
import com.joemerrill.diningreview.model.ReviewStatus;
import com.joemerrill.diningreview.repository.RestaurantRepository;
import com.joemerrill.diningreview.repository.ReviewRepository;
import com.joemerrill.diningreview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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

        if (!reviewOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Review review = reviewOptional.get();

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Request cannot be processed. Restaurant not found.");
        }

        if (adminReviewAction.isApprove()) {
            review.setStatus(ReviewStatus.APPROVED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }

        reviewRepository.save(review);

    }
}
