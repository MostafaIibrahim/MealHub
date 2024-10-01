package com.example.myapplication.model_app.utility;

import com.example.myapplication.model_app.ingreident.IngredientMeal;

import java.util.List;

public interface IngredientNetworkCallBack {
    public void ingredientResponseOnSuccessful(List<IngredientMeal> meals);
    public void onFailureResult(String errorMsg);
}
