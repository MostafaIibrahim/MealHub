package com.example.myapplication.model_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RandomMeal.class},version = 2 )
public abstract class MealDataBase extends RoomDatabase {
    private static MealDataBase instance = null;
    public abstract MealDAO getProductDAO();
    public static synchronized MealDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MealDataBase.class,"RandomMeal").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
