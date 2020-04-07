package com.foodOrder.services;

import com.foodOrder.model.FoodOrders;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */
public interface OrderService extends CrudRepository<FoodOrders, Integer> {
    Iterable<FoodOrders> findByPaymentMethodAndDeliveryMethod(String paymentMethod, String deliveryMethod);
    Iterable<FoodOrders> findByPaymentMethod(String paymentMethod);
    Iterable<FoodOrders> findByDeliveryMethod(String deliveryMethod);
}
