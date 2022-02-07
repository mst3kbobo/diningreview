package com.joemerrill.diningreview;

import com.joemerrill.diningreview.controller.AdminController;
import com.joemerrill.diningreview.controller.RestaurantController;
import com.joemerrill.diningreview.controller.ReviewController;
import com.joemerrill.diningreview.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiningReviewApplicationTests {

	@Autowired
	private AdminController adminController;
	@Autowired
	private RestaurantController restaurantController;
	@Autowired
	private ReviewController reviewController;
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertNotNull(adminController, "AdminController instance is null.");
		assertNotNull(restaurantController, "RestaurantController instance is null.");
		assertNotNull(reviewController, "ReviewController instance is null.");
		assertNotNull(userController, "UserController instance is null.");
	}

}
