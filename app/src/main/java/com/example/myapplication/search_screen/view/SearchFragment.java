package com.example.myapplication.search_screen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MealHub.R;
import com.example.myapplication.model_app.CategoryMeal;
import com.example.myapplication.model_app.CategoryMealRemoteDataSource;
import com.example.myapplication.model_app.IngredientMeal;
import com.example.myapplication.model_app.IngredientMealRemoteDataSource;
import com.example.myapplication.search_screen.category_meal.presenter_category.CategoryPresenter;
import com.example.myapplication.search_screen.category_meal.view_category.CategoryAdapter;
import com.example.myapplication.search_screen.category_meal.view_category.IViewCategory;
import com.example.myapplication.search_screen.ingredient_meal.presenter_ingredient.IngredientPresenter;
import com.example.myapplication.search_screen.ingredient_meal.view_ingredient.IViewIngredients;
import com.example.myapplication.search_screen.ingredient_meal.view_ingredient.IngredientAdapter;

import java.util.List;


public class SearchFragment extends Fragment implements IViewCategory, IViewIngredients {
    SearchView searchBar;
    RecyclerView categoryRcy, countryRcy,ingredientsRcy;
    TextView seeAll;

    CategoryPresenter categoryPresenter;
    IngredientPresenter ingredientPresenter;
    RecyclerView getCategoryRcy;
    CategoryAdapter categoryAdapter;
    IngredientAdapter ingredientAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = view.findViewById(R.id.searchView);
        countryRcy = view.findViewById(R.id.searchCountriesRecylerView);
        ingredientsRcy = view.findViewById(R.id.ingredientsRecyclerView);
        getCategoryRcy = view.findViewById(R.id.searchCategoryRecylerView);

        ingredientPresenter = new IngredientPresenter(this, IngredientMealRemoteDataSource.getInstance());
        categoryPresenter = new CategoryPresenter(this,CategoryMealRemoteDataSource.getInstance()) ;

        ingredientPresenter.requestData();
        categoryPresenter.requestData();
        categoryAttachToAdapter();
        ingredientAttachToAdapter();
//        seeAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "I am see all", Toast.LENGTH_SHORT).show();
//            }
//        });


        //Now I need to handle adapter of all of these rcyc view and seeall click listener

    }
    void categoryAttachToAdapter(){
        getCategoryRcy.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        getCategoryRcy.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext());
    }
    void ingredientAttachToAdapter(){
        ingredientsRcy.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 8);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientsRcy.setLayoutManager(layoutManager);
        ingredientAdapter = new IngredientAdapter(getContext());
    }


    @Override
    public void getCategoryMeals(List<CategoryMeal> categoryMeals) {
//        Toast.makeText(getContext(), "Category data is here and presenter successed", Toast.LENGTH_SHORT).show();
        getCategoryRcy.setAdapter(categoryAdapter);
        categoryAdapter.updateMeals(categoryMeals);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getIngredients(List<IngredientMeal> ingredients) {
        ingredientsRcy.setAdapter(ingredientAdapter);
        ingredientAdapter.updateMeals(ingredients);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(),errorMsg, Toast.LENGTH_SHORT).show();
    }
}