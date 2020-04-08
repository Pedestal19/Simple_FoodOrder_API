package com.foodOrder;

import com.foodOrder.model.FoodOrders;
import com.foodOrder.model.Meal;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */
public class SystemsTests {

    @Test
    public void testCreateReadDeleteOrder(){

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:7000/order";

        FoodOrders foodOrder = new FoodOrders(350.0, "cash", "self");
        ResponseEntity<FoodOrders> entity = restTemplate.postForEntity(url, foodOrder, FoodOrders.class);

        FoodOrders[] foodOrders = restTemplate.getForObject(url, FoodOrders[].class);
        Assertions.assertThat(foodOrders).extracting(FoodOrders::getTotalCost).contains(350.0);

        restTemplate.delete(url + "/" +entity.getBody().getId());
//        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
        FoodOrders[] foodOrderAfterDelete = restTemplate.getForObject(url, FoodOrders[].class);
        Assertions.assertThat(foodOrderAfterDelete).extracting(FoodOrders::getId).doesNotContain(entity.getBody().getId());

    }

    @Test
    public void testCreateReadDeleteMeal(){

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:7000/meal";

        Meal meal = new Meal("spaghetti", 1200);
        ResponseEntity<Meal> entity = restTemplate.postForEntity(url, meal, Meal.class);

        Meal[] meals = restTemplate.getForObject(url, Meal[].class);
        Assertions.assertThat(meals).extracting(Meal::getName).contains("spaghetti");

        restTemplate.delete(url + "/" +entity.getBody().getId());
//        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
        Meal[] mealAfterDelete = restTemplate.getForObject(url, Meal[].class);
        Assertions.assertThat(mealAfterDelete).extracting(Meal::getId).doesNotContain(entity.getBody().getId());

    }

    @Test
    public void testErrorHandlingReturnsBadRequest(){

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:7000/wrong";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }
}
