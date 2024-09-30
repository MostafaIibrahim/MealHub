package com.example.myapplication.search_screen.category_meal.view_category;

import com.example.myapplication.model_app.CategoryMeal;

import java.util.List;

public interface IViewCategory {
    public void getCategoryMeals(List<CategoryMeal> categoryMeals);
    public void showError(String errorMsg);
}
