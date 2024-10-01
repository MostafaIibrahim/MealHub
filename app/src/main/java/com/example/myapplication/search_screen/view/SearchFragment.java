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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.MealHub.R;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.search_screen.presenter.SearchPresenter;

import java.util.List;


public class SearchFragment extends Fragment implements IViewSearch  {
    private static final int FILTER_BY_NAME = 0;
    private static final int FILTER_BY_CATEGORY = 1;
    private static final int FILTER_BY_INGREDIENT = 2;
    private int currentFilter = FILTER_BY_NAME;

    SearchView searchBar;
    private RadioGroup filterRadioGroup;
    RadioButton radioName, radioCategory, radioIngredient;
    RecyclerView  countryRcy,ingredientsRcy,getCategoryRcy;
    SearchPresenter presenter;
    CountryAdapter countryAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
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
        searchBar = view.findViewById(R.id.searchView);
        filterRadioGroup = view.findViewById(R.id.filterRadioGroup);
        radioName = view.findViewById(R.id.radioName);
        radioCategory = view.findViewById(R.id.radioCategory);
        radioIngredient = view.findViewById(R.id.radioIngredient);
        presenter = new SearchPresenter(this, MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(getContext()), MealRemoteDataSourceImp.getInstance()));

        presenter.requestCategoryData();
        presenter.requestCountryData();
        presenter.requestIngredientData();


        countryAttachToAdapter();
        categoryAttachToAdapter();
        ingredientAttachToAdapter();


        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        filterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case FILTER_BY_NAME:
                        currentFilter = FILTER_BY_NAME;
                        break;
                    case FILTER_BY_CATEGORY:
                        currentFilter = FILTER_BY_CATEGORY;
                        break;
                    case FILTER_BY_INGREDIENT:
                        currentFilter = FILTER_BY_INGREDIENT;
                        break;
                }
            }
        });
    }
    private void performSearch(String query){
        switch (currentFilter) {
            case FILTER_BY_NAME:
                presenter.searchByName(query);
                break;
            case FILTER_BY_CATEGORY:
                presenter.searchByCategory(query);
                break;
            case FILTER_BY_INGREDIENT:
                presenter.searchByIngredient(query);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();


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

    void countryAttachToAdapter(){
        countryRcy.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        countryRcy.setLayoutManager(layoutManager);
        countryAdapter = new CountryAdapter(getContext(),presenter);
    }


    @Override
    public void getCategoryMeals(List<CategoryMeal> categoryMeals) {
//        Toast.makeText(getContext(), "Category data is here and presenter successed", Toast.LENGTH_SHORT).show();
        getCategoryRcy.setAdapter(categoryAdapter);
        categoryAdapter.updateMeals(categoryMeals);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSearchResults(List<Meal> meals) {
        Toast.makeText(getContext(), meals.get(0).getStrMeal(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getIngredients(List<IngredientMeal> ingredients) {
        ingredientsRcy.setAdapter(ingredientAdapter);
        ingredientAdapter.updateMeals(ingredients);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountries(List<CountryMeal> country) {
        countryRcy.setAdapter(countryAdapter);
        countryAdapter.setCountries(country);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(),errorMsg, Toast.LENGTH_SHORT).show();
    }
}