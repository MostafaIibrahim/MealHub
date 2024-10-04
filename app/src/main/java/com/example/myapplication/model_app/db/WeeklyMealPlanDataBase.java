package com.example.myapplication.model_app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model_app.WeeklyMealPlan;

@Database(entities = {WeeklyMealPlan.class},version = 1 )
public abstract class  WeeklyMealPlanDataBase extends RoomDatabase {
    private static WeeklyMealPlanDataBase instance = null;
    public abstract WeeklyMealPlanDAO getWeeklyMealDAO();
    public static synchronized WeeklyMealPlanDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),WeeklyMealPlanDataBase.class,"ScheduleMeal").fallbackToDestructiveMigration().build();
        }
        System.out.println("I am in Planning db");
        return instance;
    }
}
