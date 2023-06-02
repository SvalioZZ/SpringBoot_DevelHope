package com.example.myspringboot.service;

import com.example.myspringboot.dao.MyMealDao;
import com.example.springboot.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMealService {
    
    private MyMealDao mealDao;
    @Autowired
    public MyMealService(MyMealDao mealDao) {
        this.mealDao = mealDao;
    }
    
    public List<Meal> getMeals() {
        return mealDao.getMeals();
    }
    
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }
    
    public void deleteMeal(Meal meal) {
        mealDao.deleteMeal(meal);
    }
    
    public void updateMeal(Meal meal) {
        mealDao.updateMeal(meal);
    }
}
