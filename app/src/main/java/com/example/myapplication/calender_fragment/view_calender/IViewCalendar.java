package com.example.myapplication.calender_fragment.view_calender;

import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public interface IViewCalendar {
    void getMealsOfDay(List<WeeklyMealPlan> meal);
}
