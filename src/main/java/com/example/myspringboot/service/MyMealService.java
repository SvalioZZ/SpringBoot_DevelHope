package com.example.myspringboot.service;

import com.example.myspringboot.dao.MyMealDao;
import com.example.myspringboot.model.MyMeal;
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
    
    public List<MyMeal> getMeals() {
        return mealDao.getMeals();
    }
    
    public void addMeal(MyMeal meal) {
        mealDao.addMeal(meal);
    }
    
    public void deleteMeal(MyMeal meal) {
        mealDao.deleteMeal(meal);
    }
    
    public void updateMeal(MyMeal meal) {
        mealDao.updateMeal(meal);
    }
}
