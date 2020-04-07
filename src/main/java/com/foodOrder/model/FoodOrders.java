package com.foodOrder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */
@Entity
public class FoodOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("total_cost")
    private double totalCost;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("delivery_method")
    private String deliveryMethod;

    @OneToMany(cascade = CascadeType.ALL)
    List<Meal> meals;

    public FoodOrders(double totalCost, String paymentMethod, String deliveryMethod) {
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }

    public FoodOrders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
