package com.example.myapplication.model_app;

import androidx.lifecycle.LiveData;

import com.example.myapplication.calender_fragment.presenter_calender.PlannerPresenterCallBack;
import com.example.myapplication.model_app.db.MealLocalDataSource;
import com.example.myapplication.model_app.utility.CategroyNetworkCallBack;
import com.example.myapplication.model_app.utility.CountryNetworkCallBack;
import com.example.myapplication.model_app.utility.IngredientNetworkCallBack;
import com.example.myapplication.model_app.utility.MealNetworkCallBack;

import java.util.List;

public class MealRepositoryImp implements  MealRepository , DbcallBack{
    private MealRemoteDataSource remoteDataSource;
    private MealLocalDataSource mealLocalDataSource;
    PlannerPresenterCallBack callBack;
    private static MealRepositoryImp repo = null;
    public static MealRepositoryImp getInstance(MealLocalDataSource localSrc,MealRemoteDataSource remoteSrc){
        if (repo == null){
            repo = new MealRepositoryImp(localSrc,remoteSrc);
        }
        return repo;
    }
    private MealRepositoryImp(MealLocalDataSource localSrc,MealRemoteDataSource remoteSrc){
        remoteDataSource = remoteSrc;
        mealLocalDataSource = localSrc;
    }

    @Override
    public void getMealNetworkCallBack(MealNetworkCallBack mealNetworkCallBack){
        remoteDataSource.getMealNetworkCallBack(mealNetworkCallBack);
    }


    @Override
    public void getMealByNameNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack){
        remoteDataSource.getMealByNameNetworkCallBack(query, mealNetworkCallBack);
    }


    @Override
    public void getMealsByCategoryNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack){
        remoteDataSource.getMealsByCategoryNetworkCallBack(query, mealNetworkCallBack);
    }


    @Override
    public void getMealsByIngredientNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack){
        remoteDataSource.getMealsByIngredientNetworkCallBack(query, mealNetworkCallBack);
    }


    @Override
    public void getCategoryMealNetworkCallBack(CategroyNetworkCallBack categroyNetworkCallBack){
        remoteDataSource.getCategoryMealNetworkCallBack(categroyNetworkCallBack);
    }


    @Override
    public void getCountryMealNetworkCallBack(CountryNetworkCallBack countryNetworkCallBack){
        remoteDataSource.getCountryMealNetworkCallBack(countryNetworkCallBack);
    }

    @Override
    public void getMealByIdNetworkCallBack(String id, MealNetworkCallBack mealNetworkCallBack) {
        remoteDataSource.getMealByIdNetworkCallBack(id,mealNetworkCallBack);
    }


    @Override
    public void getIngreidentMealNetworkCallBack(IngredientNetworkCallBack ingredientNetworkCallBack){
        remoteDataSource.getIngreidentMealNetworkCallBack(ingredientNetworkCallBack);
    }

    @Override
    public void getMealByFirstLetterNetworkCallBack(String query, MealNetworkCallBack mealNetworkCallBack) {
        remoteDataSource.getMealByFirstLetterNetworkCallBack(query,mealNetworkCallBack);
    }

    @Override
    public void getMealByCountryNetworkCallBack(String country, CountryNetworkCallBack mealNetworkCallBack) {
        remoteDataSource.getMealByCountryNetworkCallBack(country,mealNetworkCallBack);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals(){ return mealLocalDataSource.getStoredMeals(); }
    @Override
    public void deleteMeal(Meal product){ mealLocalDataSource.deleteMeal(product);}
    @Override
    public void insertMeal(Meal product){ mealLocalDataSource.insertMeal(product);}
    @Override
    public Meal isMealExist(String idmeal){return mealLocalDataSource.isMealExist(idmeal);}

    @Override
    public LiveData<List<WeeklyMealPlan>> getMealsOfDay(String mealDate) {
        return mealLocalDataSource.getMealsOfDay(mealDate);
    }

    @Override
    public void insertPlannedMeal(WeeklyMealPlan plannedMeal) {
        mealLocalDataSource.insertPlannedMeal(plannedMeal);
    }

    @Override
    public void deletePlannedMeal(WeeklyMealPlan plannedMeal) {
        mealLocalDataSource.deletePlannedMeal(plannedMeal);
    }

    @Override
    public LiveData<List<WeeklyMealPlan>> getStoredPlannedMeals() {
        return mealLocalDataSource.getStoredPlannedMeals();
    }

    @Override
    public void onSuccessful(List<WeeklyMealPlan> plannedMeal) {
        callBack.onSuccessful(plannedMeal);
    }
}
