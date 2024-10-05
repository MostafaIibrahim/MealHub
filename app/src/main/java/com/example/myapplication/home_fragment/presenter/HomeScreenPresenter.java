package com.example.myapplication.home_fragment.presenter;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;
import com.example.myapplication.home_fragment.view.IView;

import java.util.List;

public class HomeScreenPresenter implements MealNetworkCallBack , CountryNetworkCallBack {
    IView view;
    MealRepository repository;
    public HomeScreenPresenter(IView view, MealRepository repo){
        this.view = view;
        repository = repo;
    }
    public void addMeal(Meal meal){
        repository.insertMeal(meal);
    }
    public void rmvMeal(Meal meal) { repository.deleteMeal(meal);}
    public void requestData(){ repository.getMealNetworkCallBack(this);  }
    public void searchByCountry(String country) {
        repository.getMealByCountryNetworkCallBack(country,this);}
    @Override
    public void mealResponseOnSuccessful(List<Meal> meals) {
        view.getRandomMeal(meals);
    }

    @Override
    public void countryResponseOnSuccessful(List<CountryMeal> meals) {

    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }

    @Override
    public void getMealByCountryResponseOnSuccessful(List<Meal> countryMeals) {
        view.getCountries(countryMeals);
    }
}
