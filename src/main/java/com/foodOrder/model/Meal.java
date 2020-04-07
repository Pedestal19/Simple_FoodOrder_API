package com.foodOrder.model;

import javax.persistence.*;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double cost;

    @ManyToOne
    FoodOrders foodOrders;

    public Meal(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Meal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
