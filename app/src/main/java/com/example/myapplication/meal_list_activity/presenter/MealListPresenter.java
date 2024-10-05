package com.example.myapplication.meal_list_activity.presenter;

import com.example.myapplication.meal_list_activity.view.IViewListOfMeals;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;
import com.example.myapplication.search_fragment.view.IViewSearch;

import java.util.List;

public class MealListPresenter implements MealNetworkCallBack, CountryNetworkCallBack, CategroyNetworkCallBack {
    IViewListOfMeals view;
    MealRepository repository;
    public MealListPresenter(IViewListOfMeals view, MealRepository repo){
            repository = repo;
            this.view = view;
    }

    public void searchByCategory(String query) {
        System.out.println("I am searching by category");
        repository.getMealsByCategoryNetworkCallBack(query,this);}

    public void searchByCountry(String country) {
        System.out.println("I am here in search by country");
        repository.getMealByCountryNetworkCallBack(country,this);}

    public void searchByIngredient(String query) { repository.getMealsByIngredientNetworkCallBack(query,this);}
    @Override
    public void mealResponseOnSuccessful(List<Meal> meals) {
        view.getMealsBySearch(meals);
    }

    @Override
    public void countryResponseOnSuccessful(List<CountryMeal> meals) {

    }

    @Override
    public void categoryResponseOnSuccessful(List<CategoryMeal> meals) {

    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }

    @Override
    public void mealByCategoryResponseOnSuccessful(List<Meal> meals) {
        view.getMealsBySearch(meals);
    }

    @Override
    public void getMealByCountryResponseOnSuccessful(List<Meal> countryMeals) {
        view.getMealsBySearch(countryMeals);
    }
}
