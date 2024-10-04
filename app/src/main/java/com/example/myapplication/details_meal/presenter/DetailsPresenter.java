package com.example.myapplication.details_meal.presenter;

import com.example.myapplication.details_meal.view.IViewDetails;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.WeeklyMealPlan;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

import java.util.List;

public class DetailsPresenter implements MealNetworkCallBack {
    MealRepository mealRepository;
    IViewDetails view;
    public DetailsPresenter(IViewDetails view , MealRepository mealRepository){
        this.view = view;
        this.mealRepository = mealRepository;
    }
    public void addToFav(Meal meal){
        mealRepository.insertMeal(meal);
    }

    public void requestMealById(String id){
        mealRepository.getMealByIdNetworkCallBack(id,this);
    }
    public void insertRequest(WeeklyMealPlan meal, String date){
        meal.setMealDate(date);
        mealRepository.insertPlannedMeal(meal);
    }
    @Override
    public void mealResponseOnSuccessful(List<Meal> meals) {
        view.getMealFromRespond(meals.get(0));
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.onFailureResult(errorMsg);
    }
}
