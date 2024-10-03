package com.example.myapplication.details_meal.view;

import com.example.myapplication.model_app.Meal;

public interface IViewDetails {
    public void getMealFromRespond(Meal meal);
    public void onFailureResult(String msg);
}
