package com.joemerrill.diningreview.repository;

import com.joemerrill.diningreview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameAndZipCode(String name, Integer zipCode);
    List<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByPeanutScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCodeAndEggScoreNotNullOrderByEggScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByDairyScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCodeAndGlutenScoreNotNullOrderByGlutenScoreDesc(Integer zipCode);
}
