package com.example.myapplication.model_app.utility;

import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.category.CategoryMealApiResponse;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.model_app.MealApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealPlannerApiService{
    /* Response of Random meal in home screen */
    @GET("api/json/v1/1/random.php")
    Call<MealApiResponse<Meal>> getRandomMealResponse();
    /* Response of the category in search screen */
    @GET("api/json/v1/1/categories.php")
    Call<CategoryMealApiResponse<CategoryMeal>> getCategoryMealResponse();

    @GET("api/json/v1/1/list.php?i=list")
    Call<MealApiResponse<IngredientMeal>> getIngredientMealResponse();

    @GET("api/json/v1/1/list.php?a=list")
    Call<MealApiResponse<CountryMeal>> getCountryMealResponse();

    @GET("api/json/v1/1/search.php")
    Call<MealApiResponse<Meal>> searchMealsByName(@Query("s") String mealName);

    @GET("api/json/v1/1/search.php")
    Call<MealApiResponse<Meal>> searchMealsByFirstLitter(@Query("f") String mealFirstLetter);
    @GET("api/json/v1/1/filter.php")
    Call<MealApiResponse<Meal>> searchMealsByCategory(@Query("c") String category);

    @GET("api/json/v1/1/filter.php")
    Call<MealApiResponse<Meal>> searchMealsByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php")
    Call<MealApiResponse<Meal>> searchMealsByCountry(@Query("a") String country);

    @GET("api/json/v1/1/lookup.php")
    Call<MealApiResponse<Meal>> searchMealsById(@Query("i") String id);
}
