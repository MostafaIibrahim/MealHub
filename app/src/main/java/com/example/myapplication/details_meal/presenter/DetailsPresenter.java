package com.example.myapplication.details_meal.presenter;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;

public class DetailsPresenter {
    MealRepository mealRepository;
    public DetailsPresenter(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }
    public void addToFav(Meal meal){
        mealRepository.insertMeal(meal);
    }
}
