package com.example.myapplication.search_screen.category_meal.presenter_category;

import com.example.myapplication.model_app.CategoryMeal;
import com.example.myapplication.model_app.CategoryMealRemoteDataSource;
import com.example.myapplication.model_app.MealPlannerNetworkCallBack;
import com.example.myapplication.search_screen.category_meal.view_category.IViewCategory;

import java.util.List;

public class CategoryPresenter implements MealPlannerNetworkCallBack<CategoryMeal> {
    CategoryMealRemoteDataSource src;
    IViewCategory view;

    public CategoryPresenter(IViewCategory view,CategoryMealRemoteDataSource src){
        this.src = src;
        this.view = view;
    }
    public void requestData(){ src.makeNetworkCall(this); }

    @Override
    public void onSuccessful(List<CategoryMeal> meals) {
        view.getCategoryMeals(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
