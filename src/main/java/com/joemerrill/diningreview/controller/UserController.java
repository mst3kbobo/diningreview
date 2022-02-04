package com.joemerrill.diningreview.controller;

import com.joemerrill.diningreview.model.User;
import com.joemerrill.diningreview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{displayName}")
    public User getUser(@PathVariable(name = "displayName") String displayName) {

        if (ObjectUtils.isEmpty(displayName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userRepository.findByDisplayName(displayName);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return userOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {

        if (ObjectUtils.isEmpty(user.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUserOptional = userRepository.findByDisplayName(user.getDisplayName());
        if (existingUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return userRepository.save(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@RequestBody User user) {

        if (ObjectUtils.isEmpty(user.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUserOptional = userRepository.findByDisplayName(user.getDisplayName());
        if (!existingUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User updatedUser = existingUserOptional.get();

        // Note: displayName should not be updated.

        if (user.getCity() != null) {
            updatedUser.setCity(user.getCity());
        }

        if (user.getState() != null) {
            updatedUser.setState(user.getState());
        }

        if (user.getZipCode() != null) {
            updatedUser.setZipCode(user.getZipCode());
        }

        if (user.getHasPeanutInterest() != null) {
            updatedUser.setHasPeanutInterest(user.getHasPeanutInterest());
        }

        if (user.getHasEggInterest() != null) {
            updatedUser.setHasEggInterest(user.getHasEggInterest());
        }

        if (user.getHasDairyInterest() != null) {
            updatedUser.setHasDairyInterest(user.getHasDairyInterest());
        }

        if (user.getHasGlutenInterest() != null) {
            updatedUser.setHasGlutenInterest(user.getHasGlutenInterest());
        }

        return userRepository.save(updatedUser);
    }


}
