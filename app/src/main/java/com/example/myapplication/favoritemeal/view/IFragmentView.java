package com.example.myapplication.favoritemeal.view;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.RandomMeal;

import java.util.List;

public interface IFragmentView {
    public LiveData<List<RandomMeal>> updateList();
    public void showError(String errorMsg);
}
