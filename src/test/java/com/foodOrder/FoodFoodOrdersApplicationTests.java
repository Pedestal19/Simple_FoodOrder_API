package com.foodOrder;

import com.foodOrder.controllers.FoodOrderController;
import com.foodOrder.controllers.MealController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FoodFoodOrdersApplicationTests {

	@Autowired
	FoodOrderController foodOrderController;

	@Autowired
	MealController mealController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(foodOrderController);
		Assert.assertNotNull(mealController);
	}


}
