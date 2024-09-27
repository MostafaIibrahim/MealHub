package com.example.myapplication.favoritemeal.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.inspirationmeal.Model.RandomMeals;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals_table")
    LiveData<List<RandomMeals>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(RandomMeals product);

    @Delete
    void deleteProduct(RandomMeals product);
}
