package com.example.myapplication.inspirationmeal.view;

import com.example.myapplication.model_app.RandomMeal;

import java.util.List;

public interface IView {
    public void getRandomMeal(List<RandomMeal> randomMeal);
    public void showError(String errorMsg);
}
