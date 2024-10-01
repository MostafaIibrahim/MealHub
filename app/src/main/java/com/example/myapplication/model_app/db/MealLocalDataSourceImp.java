package com.example.myapplication.model_app.db;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.Meal;

import java.util.List;

public class MealLocalDataSourceImp implements MealLocalDataSource {
    private Context context;
    private MealDAO mealOperations;
    private LiveData<List<Meal>> storedMeals;
    private static MealLocalDataSourceImp mealLocalSrc = null;
    Meal searchedMeal = null;

    private MealLocalDataSourceImp(Context _context){
        context = _context;
        MealDataBase mDb = MealDataBase.getInstance(context.getApplicationContext());
        mealOperations = mDb.getProductDAO();
        storedMeals = mealOperations.getAllProducts();
    }
    public static MealLocalDataSourceImp getInstance(Context m_context){
        if(mealLocalSrc == null){
            mealLocalSrc = new MealLocalDataSourceImp(m_context);
        }
        return mealLocalSrc;
    }
    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void deleteMeal(Meal product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.deleteProduct(product);
            }
        }.start();
    }

    @Override
    public void insertMeal(Meal product) {
        new Thread() {
            @Override
            public void run() {
                mealOperations.insertProduct(product);
            }
        }.start();
    }
    @Override
    public Meal isMealExist(String idmeal){
        new Thread(){
            @Override
            public void run() {
                searchedMeal = mealOperations.findMealById(idmeal);
            }
        }.start();
        return searchedMeal;
    }
}
