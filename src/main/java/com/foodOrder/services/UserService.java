package com.foodOrder.services;

import com.foodOrder.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */
public interface UserService  extends CrudRepository<User, Integer> {
}
