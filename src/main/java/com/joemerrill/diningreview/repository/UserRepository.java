package com.joemerrill.diningreview.repository;

import com.joemerrill.diningreview.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
