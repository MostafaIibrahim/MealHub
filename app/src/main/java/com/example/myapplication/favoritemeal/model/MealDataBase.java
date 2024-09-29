package com.example.myapplication.favoritemeal.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.inspirationmeal.Model.RandomMeals;

@Database(entities = {RandomMeals.class},version = 2 )
public abstract class MealDataBase extends RoomDatabase {
    private static MealDataBase instance = null;
    public abstract MealDAO getProductDAO();
    public static synchronized MealDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MealDataBase.class,"Meal").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
