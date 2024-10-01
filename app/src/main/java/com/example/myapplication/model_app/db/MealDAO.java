package com.example.myapplication.model_app.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model_app.Meal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllProducts();

    @Query("SELECT * FROM meals_table WHERE idMeal = :idMeal LIMIT 1")
    Meal findMealById(String idMeal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Meal product);

    @Delete
    void deleteProduct(Meal product);
}
