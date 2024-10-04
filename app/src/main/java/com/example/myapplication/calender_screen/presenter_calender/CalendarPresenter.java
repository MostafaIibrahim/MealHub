package com.example.myapplication.calender_screen.presenter_calender;

import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.myapplication.calender_screen.view_calender.IViewCalendar;
import com.example.myapplication.details_meal.view.IViewDetails;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public class CalendarPresenter implements ICalendarPresenter , PlannerPresenterCallBack {
    IViewCalendar view;
    MealRepository repo;
    public CalendarPresenter(IViewCalendar view , MealRepository mealRepository){
        this.view = view;
        this.repo = mealRepository;
    }

    @Override
    public void deleteRequest(WeeklyMealPlan meal){
        repo.deletePlannedMeal(meal);
    }

    //I want to use something that will get al live data
    @Override
    public LiveData<List<WeeklyMealPlan>> getUpdatedData(){
        return repo.getStoredPlannedMeals();
    }

    @Override
    public  LiveData<List<WeeklyMealPlan>> getPlannedMealsOfTheDay(String MealDate){
        System.out.println("I am in request");
         return repo.getMealsOfDay(MealDate);
    }

    @Override
    public void insertRequest(WeeklyMealPlan meal, String date){
        meal.setMealDate(date);
        repo.insertPlannedMeal(meal);
    }

    @Override
    public void onSuccessful(List<WeeklyMealPlan> meal) {
        view.getMealsOfDay(meal);
    }
}