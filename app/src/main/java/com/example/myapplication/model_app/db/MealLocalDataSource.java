package com.example.myapplication.model_app.db;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.DbcallBack;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public interface MealLocalDataSource {
    LiveData<List<Meal>> getStoredMeals();

    void deleteMeal(Meal product);

    void insertMeal(Meal product);

    Meal isMealExist(String idmeal);

    public LiveData<List<WeeklyMealPlan>> getMealsOfDay(String mealDate);

    void insertPlannedMeal(WeeklyMealPlan plannedMeal);

    void deletePlannedMeal(WeeklyMealPlan plannedMeal);

    LiveData<List<WeeklyMealPlan>> getStoredPlannedMeals();

//    boolean isMealInFav(String idMeal);
}
