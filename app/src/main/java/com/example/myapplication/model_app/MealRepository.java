package com.example.myapplication.model_app;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

import java.util.List;

public interface MealRepository {
    void getMealNetworkCallBack(MealNetworkCallBack mealNetworkCallBack);

    void getMealByNameNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    void getMealsByCategoryNetworkCallBack(String query, CategroyNetworkCallBack mealNetworkCallBack);

    void getMealsByIngredientNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    void getCategoryMealNetworkCallBack(CategroyNetworkCallBack categroyNetworkCallBack);

    void getCountryMealNetworkCallBack(CountryNetworkCallBack countryNetworkCallBack);

    public void getMealByIdNetworkCallBack(String id, MealNetworkCallBack mealNetworkCallBack);

    void getIngreidentMealNetworkCallBack(IngredientNetworkCallBack ingredientNetworkCallBack);

    public void getMealByFirstLetterNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack);

    public void getMealByCountryNetworkCallBack(String country, CountryNetworkCallBack mealNetworkCallBack);
    LiveData<List<Meal>> getStoredMeals();
    void deleteMeal(Meal product);
    void insertMeal(Meal product);
    Meal isMealExist(String idmeal);

    public LiveData<List<WeeklyMealPlan>> getMealsOfDay(String mealDate);

    void insertPlannedMeal(WeeklyMealPlan plannedMeal);

    void deletePlannedMeal(WeeklyMealPlan plannedMeal);

    LiveData<List<WeeklyMealPlan>> getStoredPlannedMeals();
}
