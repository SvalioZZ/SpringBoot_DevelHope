package com.example.springboot.controller.myController;

import com.example.springboot.model.Meal;
import com.example.springboot.service.MealService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class myMealController {
    
    private MealService service;
    
    @GetMapping(value = "/meals")
    public ResponseEntity<List<Meal>> getMealList() {
        return new ResponseEntity<>(service.getMeals(), HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(value = "/meal/{name}")
    public ResponseEntity<Meal> getMealByName(@PathVariable("name") String name) {
        return service.getMeals().stream().anyMatch(meal -> meal.getName().equalsIgnoreCase(name)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/description-match/{phrase}")
    public ResponseEntity<Meal> getMealByPhrase(@PathVariable ("phrase") String phrase) {
        return service.getMeals().stream().anyMatch(meal -> meal.getDescription().contentEquals(phrase)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/price")
    public ResponseEntity<List<Meal>> getMealByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return service.getMeals().stream().anyMatch(meal -> meal.getPrice() > min && meal.getPrice() < max) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    
    
    
    
}
