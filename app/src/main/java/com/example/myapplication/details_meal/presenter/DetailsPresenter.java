package com.example.myapplication.details_meal.presenter;

import com.example.myapplication.favoritemeal.model.MealLocalDataSource;
import com.example.myapplication.favoritemeal.view.IFragmentView;
import com.example.myapplication.inspirationmeal.Model.RandomMeals;

public class DetailsPresenter {
    MealLocalDataSource mealLocalDataSource;
    public DetailsPresenter(MealLocalDataSource mealLocalSrc){
        mealLocalDataSource = mealLocalSrc;
    }
    public void addToFav(RandomMeals meal){
        mealLocalDataSource.insertMeal(meal);
    }
}
