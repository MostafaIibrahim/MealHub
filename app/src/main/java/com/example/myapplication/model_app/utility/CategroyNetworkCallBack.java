package com.example.myapplication.model_app.utility;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.category.CategoryMeal;

import java.util.List;

public interface CategroyNetworkCallBack {
    public void categoryResponseOnSuccessful(List<CategoryMeal> meals);
    public void onFailureResult(String errorMsg);
    void mealByCategoryResponseOnSuccessful(List<Meal> meals);
}
