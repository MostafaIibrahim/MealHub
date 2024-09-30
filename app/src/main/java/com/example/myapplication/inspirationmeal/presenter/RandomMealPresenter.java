package com.example.myapplication.inspirationmeal.presenter;

import com.example.myapplication.model_app.RandomMeal;
import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.model_app.MealPlannerNetworkCallBack;
import com.example.myapplication.model_app.MealRemoteDataSource;
import com.example.myapplication.inspirationmeal.view.IView;

import java.util.List;

public class RandomMealPresenter implements MealPlannerNetworkCallBack<RandomMeal> {
    IView view;
    MealRemoteDataSource rSrc;
    MealLocalDataSource lSrc;
    public RandomMealPresenter(IView view, MealRemoteDataSource rSrc , MealLocalDataSource lSrc){
        this.view = view;
        this.rSrc = rSrc;
        this.lSrc = lSrc;
    }
    public void addMeal(RandomMeal randomMeal){
        lSrc.insertMeal(randomMeal);
    }
    public void rmvMeal(RandomMeal randomMeal) { lSrc.deleteMeal(randomMeal);}
    public void requestData(){ rSrc.makeNetworkCall(this); }
    @Override
    public void onSuccessful(List<RandomMeal> randomMeal) {
        view.getRandomMeal(randomMeal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
