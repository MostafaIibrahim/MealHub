package com.example.myapplication.calender_fragment.presenter_calender;

import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public interface PlannerPresenterCallBack {
    void onSuccessful(List<WeeklyMealPlan> meal);
}
