package com.example.myapplication.search_screen.ingredient_meal.view_ingredient;

import com.example.myapplication.model_app.CategoryMeal;
import com.example.myapplication.model_app.IngredientMeal;

import java.util.List;

public interface IViewIngredients {
    public void getIngredients(List<IngredientMeal> ingredients);
    public void showError(String errorMsg);
}
