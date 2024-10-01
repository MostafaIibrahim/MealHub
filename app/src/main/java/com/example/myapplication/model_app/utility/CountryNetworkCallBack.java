package com.example.myapplication.model_app.utility;

import com.example.myapplication.model_app.country_model.CountryMeal;

import java.util.List;

public interface CountryNetworkCallBack {
    public void countryResponseOnSuccessful(List<CountryMeal> meals);
    public void onFailureResult(String errorMsg);
}
