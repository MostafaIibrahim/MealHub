package com.example.myapplication.calender_screen.presenter_calender;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public interface ICalendarPresenter {
    void deleteRequest(WeeklyMealPlan meal);

    //I want to use something that will get al live data
    LiveData<List<WeeklyMealPlan>> getUpdatedData();

    public  LiveData<List<WeeklyMealPlan>> getPlannedMealsOfTheDay(String MealDate);

    void insertRequest(WeeklyMealPlan meal, String date);
}
