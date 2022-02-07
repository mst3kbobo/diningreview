package com.joemerrill.diningreview.repository;

import com.joemerrill.diningreview.model.Review;
import com.joemerrill.diningreview.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByStatus(ReviewStatus reviewStatus);
    List<Review> findByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);
}
