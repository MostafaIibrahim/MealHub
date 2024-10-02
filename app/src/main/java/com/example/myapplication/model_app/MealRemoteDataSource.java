package com.example.myapplication.model_app;

import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

public interface MealRemoteDataSource {
    void getMealNetworkCallBack(MealNetworkCallBack mealNetworkCallBack);

    void getMealByNameNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    void getMealsByCategoryNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    void getMealsByIngredientNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    public void getCategoryMealNetworkCallBack(CategroyNetworkCallBack categroyNetworkCallBack);

    void getCountryMealNetworkCallBack(CountryNetworkCallBack countryNetworkCallBack);

    void getIngreidentMealNetworkCallBack(IngredientNetworkCallBack ingredientNetworkCallBack);

    public void getMealByFirstLetterNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);
}
