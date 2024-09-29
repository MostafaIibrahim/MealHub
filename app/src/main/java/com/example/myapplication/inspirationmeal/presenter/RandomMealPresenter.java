package com.example.myapplication.inspirationmeal.presenter;

import com.example.myapplication.favoritemeal.model.MealLocalDataSource;
import com.example.myapplication.inspirationmeal.Model.NetworkCallBack;
import com.example.myapplication.inspirationmeal.Model.RandomMeals;
import com.example.myapplication.inspirationmeal.Model.RandomMealsRemoteDataSource;
import com.example.myapplication.inspirationmeal.view.IView;

import java.util.List;

public class RandomMealPresenter implements NetworkCallBack {
    IView view;
    RandomMealsRemoteDataSource rSrc;
    MealLocalDataSource lSrc;
    public RandomMealPresenter(IView view, RandomMealsRemoteDataSource rSrc , MealLocalDataSource lSrc){
        this.view = view;
        this.rSrc = rSrc;
        this.lSrc = lSrc;
    }
    public void addMeal(RandomMeals meal){
        lSrc.insertMeal(meal);
    }
    public void rmvMeal(RandomMeals meal) { lSrc.deleteMeal(meal);}
    public void requestData(){ rSrc.makeNetworkCall(this); }
    @Override
    public void onSuccessful(List<RandomMeals> meal) {
        view.getRandomMeal(meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
