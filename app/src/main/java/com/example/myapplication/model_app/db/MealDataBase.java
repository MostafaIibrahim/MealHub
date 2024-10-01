package com.example.myapplication.model_app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model_app.Meal;

@Database(entities = {Meal.class},version = 2 )
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
