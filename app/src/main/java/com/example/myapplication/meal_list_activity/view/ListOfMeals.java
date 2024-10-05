package com.example.myapplication.meal_list_activity.view;

import static com.example.myapplication.search_fragment.view.CategoryAdapter.CATEGORY_NAME;
import static com.example.myapplication.search_fragment.view.CountryAdapter.COUNTRY_NAME;
import static com.example.myapplication.search_fragment.view.IngredientAdapter.Ingredient_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealHub.R;
import com.example.myapplication.meal_list_activity.presenter.MealListPresenter;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.search_fragment.presenter.SearchPresenter;
import com.example.myapplication.search_fragment.view.IViewSearch;

import java.util.List;

public class ListOfMeals extends AppCompatActivity implements IViewListOfMeals {
    RecyclerView recyclerMealList;
    MealListAdapter adapter;
    String receivedObj;
    MealListPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_meals);
        recyclerMealList = findViewById(R.id.listMealRcycView);
        presenter = new MealListPresenter(this,MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(this),MealRemoteDataSourceImp.getInstance()));
        getIntentObj();
        recyclerMealList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerMealList.setLayoutManager(layoutManager);
        adapter = new MealListAdapter(this, MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(this), MealRemoteDataSourceImp.getInstance()));
        recyclerMealList.setAdapter(adapter);
        //Check on the coming intent


    }
    void getIntentObj(){
        Intent recievedIntent = getIntent();
        if(recievedIntent.hasExtra(CATEGORY_NAME)){
            receivedObj = recievedIntent.getStringExtra(CATEGORY_NAME);
            presenter.searchByCategory(receivedObj);
        }else if(recievedIntent.hasExtra(Ingredient_NAME)){
            receivedObj = recievedIntent.getStringExtra(Ingredient_NAME);
            presenter.searchByIngredient(receivedObj);
        }else if (recievedIntent.hasExtra(COUNTRY_NAME)){
            System.out.println("I am talking from activity in Country Name");
            receivedObj = recievedIntent.getStringExtra(COUNTRY_NAME);
            System.out.println(receivedObj);
            presenter.searchByCountry(receivedObj);
        }else{
            System.out.println("Intent = null");
        }
    }

    @Override
    public void getMealsBySearch(List<Meal> meals) {
        if (meals != null && !meals.isEmpty()) {
            adapter.updateMeals(meals);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this,errorMsg, Toast.LENGTH_SHORT).show();
    }
}