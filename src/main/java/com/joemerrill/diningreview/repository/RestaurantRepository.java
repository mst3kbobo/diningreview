package com.joemerrill.diningreview.repository;

import com.joemerrill.diningreview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
