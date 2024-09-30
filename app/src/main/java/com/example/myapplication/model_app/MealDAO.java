package com.example.myapplication.model_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals_table")
    LiveData<List<RandomMeals>> getAllProducts();

    @Query("SELECT * FROM meals_table WHERE idMeal = :idMeal LIMIT 1")
    RandomMeals findMealById(String idMeal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(RandomMeals product);

    @Delete
    void deleteProduct(RandomMeals product);
}
