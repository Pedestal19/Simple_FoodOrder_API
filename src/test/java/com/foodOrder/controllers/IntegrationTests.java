package com.foodOrder.controllers;

import com.foodOrder.model.FoodOrders;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    FoodOrderController foodOrderController;

    @Test
    public void testCreateReadDelete(){
        FoodOrders foodOrders = new FoodOrders(500, "card", "pickup");

        FoodOrders foodOrdersResult = foodOrderController.create(foodOrders);

        Iterable<FoodOrders> foodOrdersRead = foodOrderController.read();
        Assertions.assertThat(foodOrdersRead).last().hasFieldOrPropertyWithValue("totalCost", 500.0);

        foodOrderController.delete(foodOrdersResult.getId());
//        Assertions.assertThat(foodOrderController.read()).isEmpty();
        Assertions.assertThat(foodOrderController.findById(foodOrdersResult.getId())).isEmpty();


    }

    @Test(expected = ValidationException.class)
    public void errorHandlingValidationExceptionThrown(){
        foodOrderController.somethingIsWrong();
    }
}
