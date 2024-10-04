package com.example.myapplication.model_app.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;
@Dao
public interface WeeklyMealPlanDAO {
    @Query("SELECT * FROM meal_schedule")
    LiveData<List<WeeklyMealPlan>> getAllMeals();

    @Query("SELECT * FROM meal_schedule WHERE idMeal = :idMeal LIMIT 1")
    WeeklyMealPlan findMealById(String idMeal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(WeeklyMealPlan meal);

    @Delete
    void deleteMeal(WeeklyMealPlan meal);

    @Query("SELECT * FROM meal_schedule WHERE mealDate = :mealDatePar")
    LiveData<List<WeeklyMealPlan>> getMealsForDay(String mealDatePar);

    @Query("SELECT EXISTS(SELECT 1 FROM meal_schedule WHERE idMeal = :idMeal AND isFavorite = 1)")
    boolean isMealFavorite(String idMeal);
}
