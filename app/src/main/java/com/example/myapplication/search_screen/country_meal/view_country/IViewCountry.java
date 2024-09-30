package com.example.myapplication.search_screen.country_meal.view_country;

import com.example.myapplication.model_app.CountryMeal;
import com.example.myapplication.model_app.IngredientMeal;

import java.util.List;

public interface IViewCountry {
    public void getCountries(List<CountryMeal> country);
    public void showError(String errorMsg);
}
