package com.example.myapplication.meal_list_activity.view;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface IViewListOfMeals {
    public void getMealsBySearch(List<Meal> meals);
    public void showError(String errorMsg);
}
