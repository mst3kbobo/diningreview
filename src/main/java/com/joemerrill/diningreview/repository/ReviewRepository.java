package com.joemerrill.diningreview.repository;

import com.joemerrill.diningreview.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
