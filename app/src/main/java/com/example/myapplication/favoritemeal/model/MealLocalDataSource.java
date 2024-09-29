package com.example.myapplication.favoritemeal.model;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.inspirationmeal.Model.RandomMeals;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealOperations;
    private LiveData<List<RandomMeals>> storedMeals;
    private static MealLocalDataSource mealLocalSrc = null;
    RandomMeals searchedMeal = null;

    private MealLocalDataSource(Context _context){
        context = _context;
        MealDataBase mDb = MealDataBase.getInstance(context.getApplicationContext());
        mealOperations = mDb.getProductDAO();
        storedMeals = mealOperations.getAllProducts();
    }
    public static MealLocalDataSource getInstance(Context m_context){
        if(mealLocalSrc == null){
            mealLocalSrc = new MealLocalDataSource(m_context);
        }
        return mealLocalSrc;
    }
    public LiveData<List<RandomMeals>> getStoredMeals() {
        return storedMeals;
    }

    public void deleteMeal(RandomMeals product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.deleteProduct(product);
            }
        }.start();
    }

    public void insertMeal(RandomMeals product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.insertProduct(product);
            }
        }.start();
    }
    public RandomMeals isMealExist(String idmeal){
        new Thread(){
            @Override
            public void run() {
                searchedMeal = mealOperations.findMealById(idmeal);
            }
        }.start();
        return searchedMeal;
    }
}
