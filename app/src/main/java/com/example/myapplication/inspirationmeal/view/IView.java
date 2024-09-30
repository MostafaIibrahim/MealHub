package com.example.myapplication.inspirationmeal.view;

import com.example.myapplication.model_app.RandomMeals;

import java.util.List;

public interface IView {
    public void getRandomMeal(List<RandomMeals> meal);
    public void showError(String errorMsg);
}
