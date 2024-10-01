package com.example.myapplication.search_screen.view;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.ingreident.IngredientMeal;

import java.util.List;

public interface IViewSearch {
    public void getCountries(List<CountryMeal> country);
    public void getIngredients(List<IngredientMeal> ingredients);
    public void getCategoryMeals(List<CategoryMeal> categoryMeals);
    public void showSearchResults(List<Meal> meals);
    public void showError(String errorMsg);
}
