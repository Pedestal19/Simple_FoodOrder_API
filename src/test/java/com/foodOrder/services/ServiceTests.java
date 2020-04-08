package com.foodOrder.services;

import com.foodOrder.model.FoodOrders;
import com.foodOrder.model.Meal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @Autowired
    OrderService orderService;

    @Autowired
    MealService mealService;


    @Test
    public void testCreateReadDeleteForOrders(){

        FoodOrders foodOrders = new FoodOrders(800.0, "cash", "delivery");

        orderService.save(foodOrders);

        Iterable<FoodOrders> foodOrder = orderService.findAll();
        Assertions.assertThat(foodOrder).extracting(FoodOrders::getPaymentMethod).contains("cash");

        orderService.deleteAll();
        Assertions.assertThat(orderService.findAll()).isEmpty();

    }

    @Test
    public void testCreateReadDeleteForMeals(){

        Meal meal = new Meal("rice", 300.0);

        mealService.save(meal);

        Iterable<Meal> meals = mealService.findAll();
        Assertions.assertThat(meals).extracting(Meal::getName).contains("rice");

        mealService.deleteAll();
        Assertions.assertThat(mealService.findAll()).isEmpty();

    }
}
