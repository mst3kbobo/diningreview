package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.Restaurant;
import com.joemerrill.diningreview.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id);
    }

    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurants(
            @RequestParam(name = "zipcode") String zipCode,
            @RequestParam String interest
    ) {

        // TODO: Validate Zip Code
        Integer zip = Integer.parseInt(zipCode);

        Iterable<Restaurant> restaurants;

        switch (interest.toLowerCase()) {
            case "peanut":
                restaurants = restaurantRepository.findByZipCodeAndPeanutScoreNotNullOrderByPeanutScoreDesc(zip);
                break;
            case "egg":
                restaurants = restaurantRepository.findByZipCodeAndEggScoreNotNullOrderByEggScoreDesc(zip);
                break;
            case "dairy":
                restaurants = restaurantRepository.findByZipCodeAndDairyScoreNotNullOrderByDairyScoreDesc(zip);
                break;
            case "gluten":
                restaurants = restaurantRepository.findByZipCodeAndGlutenScoreNotNullOrderByGlutenScoreDesc(zip);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return restaurants;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {

        // Restaurants with same name and zip code should not exist
        if (ObjectUtils.isEmpty(restaurant.getName()) || ObjectUtils.isEmpty(restaurant.getZipCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Restaurant> existingRestaurantOptional =
                restaurantRepository.findByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());

        if (existingRestaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        // TODO: Validate Zip Code

        return restaurantRepository.save(restaurant);
    }
}
