package com.example.myapplication.inspirationmeal.presenter;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;
import com.example.myapplication.inspirationmeal.view.IView;

import java.util.List;

public class RandomMealPresenter implements MealNetworkCallBack {
    IView view;
    MealRepository repository;
    public RandomMealPresenter(IView view, MealRepository repo){
        this.view = view;
        repository = repo;
    }
    public void addMeal(Meal meal){
        repository.insertMeal(meal);
    }
    public void rmvMeal(Meal meal) { repository.deleteMeal(meal);}
    public void requestData(){ repository.getMealNetworkCallBack(this);  }
    @Override
    public void mealResponseOnSuccessful(List<Meal> meals) {
        view.getRandomMeal(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
