package com.example.myspringboot.dao;

import com.example.springboot.model.Meal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MyMealDao {
    private List<Meal> meals = new ArrayList<>();
    
    public List<Meal> getMeals() {
        return this.meals;
    }
    
    public void addMeal(Meal meal) {
        if (meal == null) throw new IllegalArgumentException("Meal must not be null");
        if (meal.getName() == null) throw new IllegalArgumentException("Meal name must not be null");
        if (meal.getDescription() == null) throw new IllegalArgumentException("Meal description must not be null");
        if (meal.getPrice() <= 0) throw new IllegalArgumentException("Meal price must be greater than 0");
        if (meal.getPrice() > 100) throw new IllegalArgumentException("Meal price cannot be greater than 100");
        this.meals.add(meal);
    }
    
    public void deleteMeal(Meal meal) {
        this.meals.remove(meal);
    }
    
    public void updateMeal(Meal meal) {
        this.meals.removeIf(m -> m.getName().equals(meal.getName()));
        this.meals.add(meal);
    }
    

}
