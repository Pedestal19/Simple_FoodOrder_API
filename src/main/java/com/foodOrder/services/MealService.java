package com.foodOrder.services;

import com.foodOrder.model.Meal;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */
public interface MealService extends CrudRepository<Meal, Integer> {
    Iterable<Meal> findByNameAndAndCost(String name, String cost);
    Iterable<Meal> findByName(String name);
    Iterable<Meal> findByCost(String cost);
}
