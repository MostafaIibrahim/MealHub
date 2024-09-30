package com.example.myapplication.model_app;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealPlannerApiService{
    @GET("api/json/v1/1/random.php")
    Call<MealApiResponse<RandomMeal>> getRandomMealResponse();

    @GET("api/json/v1/1/categories.php")
    Call<CategoryMealApiResponse<CategoryMeal>> getCategoryMealResponse();

    @GET("api/json/v1/1/list.php?i=list")
    Call<MealApiResponse<IngredientMeal>> getIngredientMealResponse();

}
