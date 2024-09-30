package com.example.myapplication.model_app;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryMealRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Category Retrofit connection";
    Retrofit connectRetro;
    MealPlannerApiService api;
    private static CountryMealRemoteDataSource countryMealRemoteDataSource = null;
    private CountryMealRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(MealPlannerApiService.class);
    }
    public static CountryMealRemoteDataSource getInstance(){
        if(countryMealRemoteDataSource == null){
            countryMealRemoteDataSource = new CountryMealRemoteDataSource();
        }
        return countryMealRemoteDataSource;
    }
    public void makeNetworkCall(MealPlannerNetworkCallBack mealPlannerNetworkCallBack){
        Call<MealApiResponse<CountryMeal>> call = api.getCountryMealResponse();
        call.enqueue(new Callback<MealApiResponse<CountryMeal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<CountryMeal>> call, Response<MealApiResponse<CountryMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealPlannerNetworkCallBack.onSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<CountryMeal>> call, Throwable throwable) {
                mealPlannerNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
