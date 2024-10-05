package com.example.myapplication.model_app;

import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

public interface MealRemoteDataSource {
    void getMealNetworkCallBack(MealNetworkCallBack mealNetworkCallBack);

    void getMealByNameNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    void getMealsByCategoryNetworkCallBack(String query, CategroyNetworkCallBack mealNetworkCallBack);

    void getMealsByIngredientNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    public void getCategoryMealNetworkCallBack(CategroyNetworkCallBack categroyNetworkCallBack);

    public void getMealByCountryNetworkCallBack(String country, CountryNetworkCallBack mealNetworkCallBack);

    public void getMealByIdNetworkCallBack(String id, MealNetworkCallBack mealNetworkCallBack);

    void getCountryMealNetworkCallBack(CountryNetworkCallBack countryNetworkCallBack);

    void getIngreidentMealNetworkCallBack(IngredientNetworkCallBack ingredientNetworkCallBack);


    public void getMealByFirstLetterNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);
}
