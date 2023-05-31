package com.example.springboot.controller.myController;

import com.example.springboot.model.Meal;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class myMealController {
    
    private List<Meal> meals = new ArrayList<>();
    
    @GetMapping(value = "/meals")
    public ResponseEntity<List<Meal>> getMealList() {
        return new ResponseEntity<>(meals, HttpStatusCode.valueOf(200));
    }
    
    @GetMapping(value = "/meal/{name}")
    public ResponseEntity<Meal> getMealByName(@PathVariable("name") String name) {
        return this.meals.stream().anyMatch(meal -> meal.getName().equalsIgnoreCase(name)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/description-match/{phrase}")
    public ResponseEntity<Meal> getMealByPhrase(@PathVariable ("phrase") String phrase) {
        return this.meals.stream().anyMatch(meal -> meal.getDescription().contentEquals(phrase)) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @GetMapping(value = "/meal/price")
    public ResponseEntity<List<Meal>> getMealByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return this.meals.stream().anyMatch(meal -> meal.getPrice() > min && meal.getPrice() < max) ?
                       ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    
    @PutMapping(value = "/put/meal")
    public ResponseEntity<String> putMeal(@RequestBody Meal meal) {
        this.meals.add(meal);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/update/meal/{name}")
    public ResponseEntity<String> updateMeal(@PathVariable("name") String name, @RequestBody Meal meal) {
        this.meals.removeIf(m -> m.getName().equalsIgnoreCase(name));
        this.meals.add(meal);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping(value = "/meal/{name}")
    public ResponseEntity<String> deleteMeal(@PathVariable("name") String name) {
        this.meals.removeIf(m -> m.getName().contentEquals(name));
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping(value = "/delete/meal/{price}")
    public ResponseEntity<String> deleteMeal(@PathVariable("price") double price) {
        this.meals.removeIf(m -> m.getPrice() > price);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(value = "/meal/{name}/price")
    public ResponseEntity<String> updatePriceByMealName(@PathVariable("name") String name, @RequestBody Meal meal) {
        this.meals.removeIf(m -> m.getName().equalsIgnoreCase(name));
        this.meals.add(meal);
        return ResponseEntity.ok().build();
    }
}
