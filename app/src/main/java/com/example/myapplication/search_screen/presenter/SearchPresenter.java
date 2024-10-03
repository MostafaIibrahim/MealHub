package com.example.myapplication.search_screen.presenter;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMapperModel;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;
import com.example.myapplication.search_screen.view.IViewSearch;

import java.util.List;

public class SearchPresenter implements MealNetworkCallBack, CategroyNetworkCallBack, CountryNetworkCallBack , IngredientNetworkCallBack {
    MealRepository repository;
    CountryMapperModel mapperModel;
    IViewSearch view;
    public SearchPresenter(IViewSearch view,MealRepository repo){
        repository = repo;
        this.view = view;
        mapperModel = new CountryMapperModel();
    }

    //It's instead of CategoryPresenter, I only need CallBack to be implemented
    public void requestCategoryData(){ repository.getCategoryMealNetworkCallBack(this); }
    //Implement callBack of get country onSuccessful
    public void requestCountryData(){ repository.getCountryMealNetworkCallBack(this);}
    public String getCountry(String key){
        return mapperModel.getCountryCode(key);
    }

    public void requestIngredientData(){ repository.getIngreidentMealNetworkCallBack(this);}

    public void searchByName(String query) {
        System.out.println(query);
        repository.getMealByNameNetworkCallBack(query,this);
    }

    public void searchByCategory(String query) {
        System.out.println("I am searching by category");
        repository.getMealsByCategoryNetworkCallBack(query,this);}

    public void searchByIngredient(String query) { repository.getMealsByIngredientNetworkCallBack(query,this);}

    public void searchMealByLetter(String query) { repository.getMealByFirstLetterNetworkCallBack(query,this); }

    public void searchByCountry(String country) {
        System.out.println("I am here in search by country");
        repository.getMealByCountryNetworkCallBack(country,this);}

    @Override
    public void mealResponseOnSuccessful(List<Meal> meals) {
        view.getMealsBySearch(meals);
    }

    @Override
    public void categoryResponseOnSuccessful(List<CategoryMeal> meals) {
        System.out.println("Category on successful");
        view.getCategoryMeals(meals);
    }

    @Override
    public void countryResponseOnSuccessful(List<CountryMeal> meals) {
        view.getCountries(meals);
    }

    @Override
    public void ingredientResponseOnSuccessful(List<IngredientMeal> meals) {
        view.getIngredients(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
