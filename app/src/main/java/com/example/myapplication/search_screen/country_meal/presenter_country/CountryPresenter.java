package com.example.myapplication.search_screen.country_meal.presenter_country;

import com.example.myapplication.model_app.CountryMapperModel;
import com.example.myapplication.model_app.CountryMeal;
import com.example.myapplication.model_app.CountryMealRemoteDataSource;
import com.example.myapplication.model_app.IngredientMeal;
import com.example.myapplication.model_app.IngredientMealRemoteDataSource;
import com.example.myapplication.model_app.MealPlannerNetworkCallBack;
import com.example.myapplication.search_screen.country_meal.view_country.IViewCountry;
import com.example.myapplication.search_screen.ingredient_meal.view_ingredient.IViewIngredients;

import java.util.List;

public class CountryPresenter implements MealPlannerNetworkCallBack<CountryMeal> {
    CountryMealRemoteDataSource src;
    IViewCountry view;
    CountryMapperModel mapperModel;
    public CountryPresenter(IViewCountry view,CountryMealRemoteDataSource src){
        this.src = src;
        this.view = view;
        mapperModel = new CountryMapperModel();
    }
    public void requestData(){ src.makeNetworkCall(this); }

    public String getCountry(String key){
        return mapperModel.getCountryCode(key);
    }
    @Override
    public void onSuccessful(List<CountryMeal> countries) {
        view.getCountries(countries);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
