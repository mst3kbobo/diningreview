package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.Restaurant;
import com.joemerrill.diningreview.model.Review;
import com.joemerrill.diningreview.model.ReviewStatus;
import com.joemerrill.diningreview.model.User;
import com.joemerrill.diningreview.repository.RestaurantRepository;
import com.joemerrill.diningreview.repository.ReviewRepository;
import com.joemerrill.diningreview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReview(@RequestBody Review review) {

        if (ObjectUtils.isEmpty(review.getSubmittedBy()) || ObjectUtils.isEmpty(review.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(review.getPeanutScore()) &&
                ObjectUtils.isEmpty(review.getEggScore()) &&
                ObjectUtils.isEmpty(review.getDairyScore()) &&
                ObjectUtils.isEmpty(review.getGlutenScore())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userRepository.findByDisplayName(review.getSubmittedBy());

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Request cannot be processed.");
        }

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Request cannot be processed. Restaurant not found.");
        }

        review.setStatus(ReviewStatus.PENDING);

        return reviewRepository.save(review);
    }

}
