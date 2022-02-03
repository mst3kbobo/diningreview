package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.Review;
import com.joemerrill.diningreview.repository.ReviewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
