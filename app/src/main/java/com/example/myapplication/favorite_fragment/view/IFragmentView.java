package com.example.myapplication.favorite_fragment.view;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public interface IFragmentView {
    public LiveData<List<Meal>> updateList();
    public void showError(String errorMsg);
}
