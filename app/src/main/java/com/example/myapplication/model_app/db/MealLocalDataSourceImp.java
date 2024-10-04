package com.example.myapplication.model_app.db;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model_app.DbcallBack;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.Collections;
import java.util.List;

public class MealLocalDataSourceImp implements MealLocalDataSource {
    private Context context;
    private MealDAO mealOperations;
    private WeeklyMealPlanDAO mealPlanDAO;
    private LiveData<List<WeeklyMealPlan>> storedMealOfWeek;
    private LiveData<List<Meal>> storedMeals;
    private static MealLocalDataSourceImp mealLocalSrc = null;
    Meal searchedMeal = null;
    WeeklyMealPlan searchedPlannedMeal = null;
    List<WeeklyMealPlan> mealPlanList = null;
    private MealLocalDataSourceImp(Context _context){
        context = _context;
        MealDataBase mDb = MealDataBase.getInstance(context.getApplicationContext());
        WeeklyMealPlanDataBase planedMealDb = WeeklyMealPlanDataBase.getInstance(context.getApplicationContext());
        mealOperations = mDb.getProductDAO();
        mealPlanDAO = planedMealDb.getWeeklyMealDAO();
        storedMealOfWeek = mealPlanDAO.getAllMeals();
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
                //call function that is implemented by someOne
            }
        }.start();

        return searchedMeal;
    }

    @Override
    public LiveData<List<WeeklyMealPlan>> getMealsOfDay(String mealDate) {
        System.out.println("In MealLocal source before thread");
        return mealPlanDAO.getMealsForDay(mealDate);
    }

    @Override
    public void insertPlannedMeal(WeeklyMealPlan plannedMeal) {
        System.out.println("I am in inserting operation in db");
        new Thread(() ->{ mealPlanDAO.insertMeal(plannedMeal);}).start();
    }

    @Override
    public void deletePlannedMeal(WeeklyMealPlan plannedMeal) {
        new Thread(() ->{ mealPlanDAO.deleteMeal(plannedMeal);}).start();
    }

    @Override
    public LiveData<List<WeeklyMealPlan>> getStoredPlannedMeals() {
        return storedMealOfWeek;
    }
}
