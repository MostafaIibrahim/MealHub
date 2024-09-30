package com.example.myapplication.search_screen.ingredient_meal.presenter_ingredient;

import com.example.myapplication.model_app.CategoryMeal;
import com.example.myapplication.model_app.CategoryMealRemoteDataSource;
import com.example.myapplication.model_app.IngredientMeal;
import com.example.myapplication.model_app.IngredientMealRemoteDataSource;
import com.example.myapplication.model_app.MealPlannerNetworkCallBack;
import com.example.myapplication.search_screen.category_meal.view_category.IViewCategory;
import com.example.myapplication.search_screen.ingredient_meal.view_ingredient.IViewIngredients;

import java.util.List;

public class IngredientPresenter implements MealPlannerNetworkCallBack<IngredientMeal> {
    IngredientMealRemoteDataSource src;
    IViewIngredients view;

    public IngredientPresenter(IViewIngredients view,IngredientMealRemoteDataSource src){
        this.src = src;
        this.view = view;
    }
    public void requestData(){ src.makeNetworkCall(this); }

    @Override
    public void onSuccessful(List<IngredientMeal> ingredients) {
        view.getIngredients(ingredients);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
