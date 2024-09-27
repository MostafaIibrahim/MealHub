package com.example.myapplication.inspirationmeal.Model;

import java.util.List;

public interface NetworkCallBack {
    public void onSuccessful(List<RandomMeals> meal);
    public void onFailureResult(String errorMsg);
}
