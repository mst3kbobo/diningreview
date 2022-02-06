package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.Restaurant;
import com.joemerrill.diningreview.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final String usZipCodeRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
    private final Pattern usZipCodePattern = Pattern.compile(usZipCodeRegex);

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

        Integer zip;
        try {
            zip = Integer.parseInt(zipCode);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        validateZipCode(zip);

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

        validateZipCode(restaurant.getZipCode());

        return restaurantRepository.save(restaurant);
    }

    /**
     * Validate a US formatted Zip Code
     * @param zipCode Zip code to validate
     */
    private void validateZipCode(Integer zipCode) {

        String zipCodeString = zipCode.toString();
        if (!usZipCodePattern.matcher(zipCodeString).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
