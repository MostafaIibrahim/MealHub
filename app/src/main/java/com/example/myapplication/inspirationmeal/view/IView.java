package com.example.myapplication.inspirationmeal.view;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface IView {
    public void getRandomMeal(List<Meal> meal);
    public void showError(String errorMsg);
}
