package com.example.myapplication.home_fragment.view;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface IView {
    public void getRandomMeal(List<Meal> meal);
    public void showError(String errorMsg);
    public void getCountries(List<Meal>  countryMeal);
    public void getBreakFast(List<Meal> breakfastMeals);
}
