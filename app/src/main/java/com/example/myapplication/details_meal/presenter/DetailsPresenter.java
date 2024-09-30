package com.example.myapplication.details_meal.presenter;

import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.model_app.RandomMeals;

public class DetailsPresenter {
    MealLocalDataSource mealLocalDataSource;
    public DetailsPresenter(MealLocalDataSource mealLocalSrc){
        mealLocalDataSource = mealLocalSrc;
    }
    public void addToFav(RandomMeals meal){
        mealLocalDataSource.insertMeal(meal);
    }
}
