package com.example.myapplication.inspirationmeal.Model;

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
    RandomMealAPI api;
    private static RandomMealsRemoteDataSource randomMealSrc = null;
    private RandomMealsRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(RandomMealAPI.class);
    }
    public static RandomMealsRemoteDataSource getInstance(){
        if(randomMealSrc == null){
            randomMealSrc = new RandomMealsRemoteDataSource();
        }
        return randomMealSrc;
    }
    public void makeNetworkCall(NetworkCallBack networkCallBack){
        Call<JsonRandomMealsResponse> call = api.getRasonse();
        call.enqueue(new Callback<JsonRandomMealsResponse>() {
            @Override
            public void onResponse(Call<JsonRandomMealsResponse> call, Response<JsonRandomMealsResponse> response) {
                Log.i(TAG, "onResponse: Successed");
                networkCallBack.onSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<JsonRandomMealsResponse> call, Throwable throwable) {
                networkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
