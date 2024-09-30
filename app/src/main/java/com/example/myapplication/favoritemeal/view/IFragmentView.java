package com.example.myapplication.favoritemeal.view;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.RandomMeals;

import java.util.List;

public interface IFragmentView {
    public LiveData<List<RandomMeals>> updateList();
    public void showError(String errorMsg);
}
