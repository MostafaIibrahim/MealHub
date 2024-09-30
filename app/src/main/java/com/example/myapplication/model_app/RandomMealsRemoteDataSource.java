package com.example.myapplication.model_app;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomMealsRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Retrofit connection";
    Retrofit connectRetro;
    MealPlannerApiService api;
    private static RandomMealsRemoteDataSource randomMealSrc = null;
    private RandomMealsRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(MealPlannerApiService.class);
    }
    public static RandomMealsRemoteDataSource getInstance(){
        if(randomMealSrc == null){
            randomMealSrc = new RandomMealsRemoteDataSource();
        }
        return randomMealSrc;
    }
    public void makeNetworkCall(MealPlannerNetworkCallBack mealPlannerNetworkCallBack){
        Call<ApiMealResponse<RandomMeals>> call = api.getRandomMealResponse();
        call.enqueue(new Callback<ApiMealResponse<RandomMeals>>() {
            @Override
            public void onResponse(Call<ApiMealResponse<RandomMeals>> call, Response<ApiMealResponse<RandomMeals>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealPlannerNetworkCallBack.onSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<ApiMealResponse<RandomMeals>> call, Throwable throwable) {
                mealPlannerNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
