package com.example.myapplication.model_app.utility;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface MealNetworkCallBack {
    public void mealResponseOnSuccessful(List<Meal> meals);
    public void onFailureResult(String errorMsg);
}
