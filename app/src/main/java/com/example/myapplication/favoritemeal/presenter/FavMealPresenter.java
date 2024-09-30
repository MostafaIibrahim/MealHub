package com.example.myapplication.favoritemeal.presenter;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.favoritemeal.view.IFragmentView;
import com.example.myapplication.model_app.RandomMeals;

import java.util.List;

public class FavMealPresenter {
    MealLocalDataSource mealLocalSrc;
    IFragmentView view;
    public FavMealPresenter(MealLocalDataSource mealLocalSrc ,IFragmentView view){
        this.mealLocalSrc = mealLocalSrc;
        this.view = view;
    }
    //Function to request to delete Data from productLocal
    public void deleteRequest(RandomMeals meal){
        mealLocalSrc.deleteMeal(meal);
    }

    //I want to use something that will get al live data
    public LiveData<List<RandomMeals>> getUpdatedData(){
        return mealLocalSrc.getStoredMeals();
    }
}
