package com.example.myapplication.model_app;


import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealOperations;
    private LiveData<List<RandomMeal>> storedMeals;
    private static MealLocalDataSource mealLocalSrc = null;
    RandomMeal searchedRandomMeal = null;

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
    public LiveData<List<RandomMeal>> getStoredMeals() {
        return storedMeals;
    }

    public void deleteMeal(RandomMeal product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.deleteProduct(product);
            }
        }.start();
    }

    public void insertMeal(RandomMeal product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.insertProduct(product);
            }
        }.start();
    }
    public RandomMeal isMealExist(String idmeal){
        new Thread(){
            @Override
            public void run() {
                searchedRandomMeal = mealOperations.findMealById(idmeal);
            }
        }.start();
        return searchedRandomMeal;
    }
}
