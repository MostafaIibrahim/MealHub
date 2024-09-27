package com.example.myapplication.inspirationmeal.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMealAPI {
    @GET("api/json/v1/1/random.php")
    Call<JsonRandomMealsResponse> getRasonse();
}
