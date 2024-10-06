package com.example.myapplication.favorite_fragment.presenter;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.favorite_fragment.view.IFragmentView;

import java.util.List;

public class FavMealPresenter {
    MealRepository repository;
    IFragmentView view;
    public FavMealPresenter(MealRepository repo , IFragmentView view){
        repository = repo;
        this.view = view;
    }
    //Function to request to delete Data from productLocal
    public void deleteRequest(Meal meal){
        repository.deleteMeal(meal);
        meal.setIsfav(false);
    }
    public void addRequest(Meal meal){
        repository.insertMeal(meal);
        meal.setIsfav(true);
    }
    //I want to use something that will get al live data
    public LiveData<List<Meal>> getUpdatedData(){
        return repository.getStoredMeals();
    }
}
