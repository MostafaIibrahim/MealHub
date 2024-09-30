package com.example.myapplication.model_app;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryMealRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Category Retrofit connection";
    Retrofit connectRetro;
    MealPlannerApiService api;
    private static CategoryMealRemoteDataSource categoryMealSrc = null;
    private CategoryMealRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(MealPlannerApiService.class);
    }
    public static CategoryMealRemoteDataSource getInstance(){
        if(categoryMealSrc == null){
            categoryMealSrc = new CategoryMealRemoteDataSource();
        }
        return categoryMealSrc;
    }
    public void makeNetworkCall(MealPlannerNetworkCallBack mealPlannerNetworkCallBack){
        Call<CategoryMealApiResponse<CategoryMeal>> call = api.getCategoryMealResponse();
        call.enqueue(new Callback<CategoryMealApiResponse<CategoryMeal>>() {
            @Override
            public void onResponse(Call<CategoryMealApiResponse<CategoryMeal>> call, Response<CategoryMealApiResponse<CategoryMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealPlannerNetworkCallBack.onSuccessful(response.body().categories);

            }

            @Override
            public void onFailure(Call<CategoryMealApiResponse<CategoryMeal>> call, Throwable throwable) {
                mealPlannerNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
