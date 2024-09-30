package com.example.myapplication.model_app;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Retrofit connection";
    Retrofit connectRetro;
    MealPlannerApiService api;
    private static MealRemoteDataSource randomMealSrc = null;
    private MealRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(MealPlannerApiService.class);
    }
    public static MealRemoteDataSource getInstance(){
        if(randomMealSrc == null){
            randomMealSrc = new MealRemoteDataSource();
        }
        return randomMealSrc;
    }
    public void makeNetworkCall(MealPlannerNetworkCallBack mealPlannerNetworkCallBack){
        Call<MealApiResponse<RandomMeal>> call = api.getRandomMealResponse();
        call.enqueue(new Callback<MealApiResponse<RandomMeal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<RandomMeal>> call, Response<MealApiResponse<RandomMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealPlannerNetworkCallBack.onSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<RandomMeal>> call, Throwable throwable) {
                mealPlannerNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
