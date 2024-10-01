package com.example.myapplication.model_app;

import android.util.Log;

import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.category.CategoryMealApiResponse;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealPlannerApiService;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImp implements MealRemoteDataSource {
    final static String url = "https://www.themealdb.com/";
    public static final String TAG = "Remote retrofit connection";
    Retrofit connectToRetro;
    MealPlannerApiService serviceCall;
    private static MealRemoteDataSourceImp randomMealSrc = null;
    private MealRemoteDataSourceImp() {
        connectToRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceCall = connectToRetro.create(MealPlannerApiService.class);
    }
    public static MealRemoteDataSourceImp getInstance() {
        if (randomMealSrc == null) {
            randomMealSrc = new MealRemoteDataSourceImp();
        }
        return randomMealSrc;
    }

    @Override
    public void getMealNetworkCallBack(MealNetworkCallBack mealNetworkCallBack) {
        serviceCall.getRandomMealResponse().enqueue(new Callback<MealApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<Meal>> call, Response<MealApiResponse<Meal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealNetworkCallBack.mealResponseOnSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<Meal>> call, Throwable throwable) {
                mealNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getMealByNameNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack) {
        serviceCall.searchMealsByName(query).enqueue(new Callback<MealApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<Meal>> call, Response<MealApiResponse<Meal>> response) {
                Log.i(TAG, "onResponse: Successed");
                if (response.body() != null && response.body().meals != null) {
                    // Pass the meals to the callback if the response body and meals are not null
                    mealNetworkCallBack.mealResponseOnSuccessful(response.body().meals);
                } else {
                    // Handle case where the response body or meals list is null
                    mealNetworkCallBack.onFailureResult("Meals not found");
                    Log.e(TAG, "Meals not found in response");
                }

            }

            @Override
            public void onFailure(Call<MealApiResponse<Meal>> call, Throwable throwable) {
                mealNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getMealsByCategoryNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack) {
        serviceCall.searchMealsByCategory(query).enqueue(new Callback<MealApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<Meal>> call, Response<MealApiResponse<Meal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealNetworkCallBack.mealResponseOnSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<Meal>> call, Throwable throwable) {
                mealNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getMealsByIngredientNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack) {
        serviceCall.searchMealsByIngredient(query).enqueue(new Callback<MealApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<Meal>> call, Response<MealApiResponse<Meal>> response) {
                Log.i(TAG, "onResponse: Successed");
                mealNetworkCallBack.mealResponseOnSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<Meal>> call, Throwable throwable) {
                mealNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getCategoryMealNetworkCallBack(CategroyNetworkCallBack categroyNetworkCallBack){
        serviceCall.getCategoryMealResponse().enqueue(new Callback<CategoryMealApiResponse<CategoryMeal>>() {
            @Override
            public void onResponse(Call<CategoryMealApiResponse<CategoryMeal>> call, Response<CategoryMealApiResponse<CategoryMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                categroyNetworkCallBack.categoryResponseOnSuccessful(response.body().categories);

            }

            @Override
            public void onFailure(Call<CategoryMealApiResponse<CategoryMeal>> call, Throwable throwable) {
                categroyNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getCountryMealNetworkCallBack(CountryNetworkCallBack countryNetworkCallBack){
        serviceCall.getCountryMealResponse().enqueue(new Callback<MealApiResponse<CountryMeal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<CountryMeal>> call, Response<MealApiResponse<CountryMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                countryNetworkCallBack.countryResponseOnSuccessful(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealApiResponse<CountryMeal>> call, Throwable throwable) {
                countryNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getIngreidentMealNetworkCallBack(IngredientNetworkCallBack ingredientNetworkCallBack){
        serviceCall.getIngredientMealResponse().enqueue(new Callback<MealApiResponse<IngredientMeal>>() {
            @Override
            public void onResponse(Call<MealApiResponse<IngredientMeal>> call, Response<MealApiResponse<IngredientMeal>> response) {
                Log.i(TAG, "onResponse: Successed");
                ingredientNetworkCallBack.ingredientResponseOnSuccessful(response.body().meals);
            }
            @Override
            public void onFailure(Call<MealApiResponse<IngredientMeal>> call, Throwable throwable) {
                ingredientNetworkCallBack.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
