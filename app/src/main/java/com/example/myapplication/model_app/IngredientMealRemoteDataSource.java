package com.example.myapplication.model_app;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientMealRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Category Retrofit connection";
    Retrofit connectRetro;
    MealPlannerApiService api;
    private static IngredientMealRemoteDataSource ingredientMealRemoteDataSource = null;
    private IngredientMealRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(MealPlannerApiService.class);
    }
    public static IngredientMealRemoteDataSource getInstance(){
        if(ingredientMealRemoteDataSource == null){
            ingredientMealRemoteDataSource = new IngredientMealRemoteDataSource();
        }
        return ingredientMealRemoteDataSource;
    }
    public void makeNetworkCall(MealPlannerNetworkCallBack mealPlannerNetworkCallBack){
        Call<MealApiResponse<IngredientMeal>> call = api.getIngredientMealResponse();
        call.enqueue(new Callback<MealApiResponse<IngredientMeal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<IngredientMeal>> call, Response<MealApiResponse<IngredientMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealPlannerNetworkCallBack.onSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<IngredientMeal>> call, Throwable throwable) {
                mealPlannerNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
