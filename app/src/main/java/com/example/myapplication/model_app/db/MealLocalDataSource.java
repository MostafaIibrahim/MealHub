package com.example.myapplication.model_app.db;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface MealLocalDataSource {
    LiveData<List<Meal>> getStoredMeals();

    void deleteMeal(Meal product);

    void insertMeal(Meal product);

    Meal isMealExist(String idmeal);
}
