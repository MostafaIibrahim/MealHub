package com.example.myapplication.model_app;

import java.util.List;

public interface MealPlannerNetworkCallBack<T> {
    public void onSuccessful(List<T> meals);
    public void onFailureResult(String errorMsg);
}
