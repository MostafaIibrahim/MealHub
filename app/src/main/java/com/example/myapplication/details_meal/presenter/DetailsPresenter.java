package com.example.myapplication.details_meal.presenter;

import com.example.myapplication.model_app.RandomMeal;
import com.example.myapplication.model_app.MealLocalDataSource;

public class DetailsPresenter {
    MealLocalDataSource mealLocalDataSource;
    public DetailsPresenter(MealLocalDataSource mealLocalSrc){
        mealLocalDataSource = mealLocalSrc;
    }
    public void addToFav(RandomMeal randomMeal){
        mealLocalDataSource.insertMeal(randomMeal);
    }
}
