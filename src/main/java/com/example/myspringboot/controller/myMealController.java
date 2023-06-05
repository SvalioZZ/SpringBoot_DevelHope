package com.example.myspringboot.controller;

import com.example.myspringboot.service.MyMealService;
import com.example.myspringboot.model.MyMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class myMealController {
    
    private MyMealService mealService;
    
    @Autowired
    public myMealController(MyMealService mealService) {
        this.mealService = mealService;
    }
    
    @GetMapping(value = "/get/meals")
    public ResponseEntity<List<MyMeal>> getMealList() {
        return new ResponseEntity<>(mealService.getMeals(), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(value = "/meal/{name}")
    public ResponseEntity<MyMeal> getMealByName(@PathVariable("name") String name) {
        return mealService.getMeals().stream().anyMatch(meal -> meal.getName().equalsIgnoreCase(name)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/description-match/{phrase}")
    public ResponseEntity<MyMeal> getMealByPhrase(@PathVariable ("phrase") String phrase) {
        return mealService.getMeals().stream().anyMatch(meal -> meal.getDescription().contentEquals(phrase)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/price")
    public ResponseEntity<List<MyMeal>> getMealByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return mealService.getMeals().stream().anyMatch(meal -> meal.getPrice() > min && meal.getPrice() < max) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @PutMapping(value = "/put/meal")
    public ResponseEntity<String> putMeal(@RequestBody MyMeal meal) {
        try {
            mealService.getMeals().add(meal);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/update/meal/{name}")
    public ResponseEntity<String> updateMeal(@PathVariable("name") String name, @RequestBody MyMeal meal) {
        mealService.getMeals().removeIf(m -> m.getName().equalsIgnoreCase(name));
        mealService.getMeals().add(meal);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping(value = "/meal/{name}")
    public ResponseEntity<String> deleteMeal(@PathVariable("name") String name) {
        mealService.getMeals().removeIf(m -> m.getName().contentEquals(name));
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping(value = "/delete/meal/{price}")
    public ResponseEntity<String> deleteMeal(@PathVariable("price") double price) {
        mealService.getMeals().removeIf(m -> m.getPrice() > price);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(value = "/meal/{name}/price")
    public ResponseEntity<String> updatePriceByMealName(@PathVariable("name") String name, @RequestBody MyMeal meal) {
        mealService.getMeals().removeIf(m -> m.getName().equalsIgnoreCase(name));
        mealService.getMeals().add(meal);
        return ResponseEntity.ok().build();
    }
}
