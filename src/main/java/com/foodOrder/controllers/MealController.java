package com.foodOrder.controllers;

import com.foodOrder.model.Meal;
import com.foodOrder.services.MealService;
import com.foodOrder.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/7/2020.
 */

@RestController
public class MealController {

    @Autowired
    MealService mealService;

    @PostMapping("/meal")
    Meal create(@Valid @RequestBody Meal meal){
        return mealService.save(meal);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }

    @GetMapping("/meal")
    Iterable<Meal> read(){
        return mealService.findAll();
    }

    @PutMapping("/meal")
    ResponseEntity<Meal> update(@RequestBody Meal meal){
        if (mealService.findById(meal.getId()).isPresent()) {
            return new ResponseEntity(mealService.save(meal), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(meal, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/meal/{id}")
    void delete(@PathVariable Integer id){
        mealService.deleteById(id);
    }

    @GetMapping("/meal/{id}")
    Optional<Meal> findById(@PathVariable Integer id){
        return mealService.findById(id);
    }

    @GetMapping("/meal/search")
    Iterable<Meal> findByQuery(
            @RequestParam(value = "name", required = false) String name, @RequestParam(value= "cost", required = false) String cost)
    {
        if(name != null && cost != null)
            return mealService.findByNameAndAndCost(name, cost);
        else if(name != null)
            return mealService.findByName(name);
        else if(cost != null)
            return mealService.findByCost(cost);
        else
            return mealService.findAll();
    }



}
