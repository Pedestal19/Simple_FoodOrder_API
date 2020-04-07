package com.foodOrder.controllers;

import com.foodOrder.model.FoodOrders;
import com.foodOrder.services.OrderService;
import com.foodOrder.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */

@RestController
public class FoodOrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    FoodOrders create(@Valid @RequestBody FoodOrders foodOrders){
        return orderService.save(foodOrders);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }

    @GetMapping("/order")
    Iterable<FoodOrders> read(){
        return orderService.findAll();
    }

    @PutMapping("/order")
    ResponseEntity<FoodOrders> update(@RequestBody FoodOrders foodOrders){
        if (orderService.findById(foodOrders.getId()).isPresent()) {
            if(foodOrders.getMeals() != null && !foodOrders.getMeals().isEmpty() && foodOrders.getMeals().size()>0){
                double total_cost=0;
                for(int i = 0; i <= foodOrders.getMeals().size(); i++){
                    total_cost = total_cost+foodOrders.getMeals().get(i).getCost();
                }
                foodOrders.setTotalCost(total_cost);
            }
            return new ResponseEntity(orderService.save(foodOrders), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(foodOrders, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/order/{id}")
    void delete(@PathVariable Integer id){
        orderService.deleteById(id);
    }

    @GetMapping("/order/{id}")
    Optional<FoodOrders> findById(@PathVariable Integer id){
        return orderService.findById(id);
    }

    @GetMapping("/order/search")
    Iterable<FoodOrders> findByQuery(
            @RequestParam(value = "paymentMethod", required = false) String paymentMethod, @RequestParam(value= "deliveryMethod", required = false) String deliveryMethod)
    {
        if(paymentMethod != null && deliveryMethod != null)
            return orderService.findByPaymentMethodAndDeliveryMethod(paymentMethod, deliveryMethod);
        else if(paymentMethod != null)
            return orderService.findByPaymentMethod(paymentMethod);
        else if(deliveryMethod != null)
            return orderService.findByDeliveryMethod(deliveryMethod);
        else
            return orderService.findAll();
    }

    @GetMapping("/wrong")
    FoodOrders somethingIsWrong(){
        throw new ValidationException("Something is wrong");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e){
        return e.getMessage();
    }

}
