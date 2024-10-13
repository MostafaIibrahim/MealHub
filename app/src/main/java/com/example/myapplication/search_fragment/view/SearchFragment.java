package com.example.myapplication.search_fragment.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SearchView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.MealHub.R;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.category.CategoryMeal;
import com.example.myapplication.model_app.country_model.CountryMeal;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.model_app.ingreident.IngredientMeal;
import com.example.myapplication.search_fragment.presenter.SearchPresenter;

import java.util.List;


public class SearchFragment extends Fragment implements IViewSearch  {
    private static final int FILTER_BY_NAME = 0;
    private static final int FILTER_BY_CATEGORY = 1;
    private static final int FILTER_BY_INGREDIENT = 2;
    private int currentFilter = FILTER_BY_NAME;
    LottieAnimationView noInternetAnimation;
    LinearLayout linearLayout;
    SearchView searchBar;
    private RadioGroup filterRadioGroup;
    RadioButton radioName, radioCategory, radioIngredient;
    RecyclerView  countryRcy,ingredientsRcy,getCategoryRcy,searchRcy;
    SearchPresenter presenter;
    CountryAdapter countryAdapter;
    CategoryAdapter categoryAdapter;
    IngredientAdapter ingredientAdapter;
    BySearchAdapter bySearchAdapter;
    ScrollView scrollView;
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
        initializeView(view);
        if(isConnectedToNetwork() != true){
            noInternetAnimation.setVisibility(View.VISIBLE);
            scrollView.setVisibility(view.GONE);
        }else{
            noInternetAnimation.setVisibility(View.GONE);
            scrollView.setVisibility(view.VISIBLE);
        }
        presenter = new SearchPresenter(this, MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(getContext()), MealRemoteDataSourceImp.getInstance()));
        setupRecyclerViews();
        setupListener();
        loadInitialData();
    }

    private void initializeView(View view){
        searchBar = view.findViewById(R.id.searchView);
        countryRcy = view.findViewById(R.id.searchCountriesRecylerView);
        ingredientsRcy = view.findViewById(R.id.ingredientsRecyclerView);
        getCategoryRcy = view.findViewById(R.id.searchCategoryRecylerView);
        searchBar = view.findViewById(R.id.searchView);
        filterRadioGroup = view.findViewById(R.id.filterRadioGroup);
        radioName = view.findViewById(R.id.radioName);
        radioCategory = view.findViewById(R.id.radioCategory);
        radioIngredient = view.findViewById(R.id.radioIngredient);
        searchRcy = view.findViewById(R.id.search_results_recyclerview);
        linearLayout = view.findViewById(R.id.linearLayoutListBy);
        noInternetAnimation = view.findViewById(R.id.searchNoInternet);
        scrollView = view.findViewById(R.id.searchScrollView);
    }

    private void setupRecyclerViews() {
        setupRecyclerView(countryRcy, new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
        setupRecyclerView(ingredientsRcy, new GridLayoutManager(getContext(), 4, RecyclerView.HORIZONTAL, false));
        setupRecyclerView(getCategoryRcy, new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
        setupRecyclerView(searchRcy, new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        countryAdapter = new CountryAdapter(getContext(), presenter);
        categoryAdapter = new CategoryAdapter(getContext());
        ingredientAdapter = new IngredientAdapter(getContext());
        bySearchAdapter = new BySearchAdapter(getContext(),presenter);
    }

    private void setupRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupListener(){
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchRcy.setVisibility(View.VISIBLE);
                performSearch(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.isEmpty()) {
                    linearLayout.setVisibility(View.GONE);
                    searchRcy.setVisibility(View.VISIBLE);
                    performSearch(s);
                } else {
                    searchRcy.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        filterRadioGroup.setOnCheckedChangeListener( (RadioGroup radioGroup, int id) -> {
                if(id == R.id.radioName){ currentFilter = FILTER_BY_NAME; }
                else if(id == R.id.radioIngredient){ currentFilter = FILTER_BY_INGREDIENT; }
                else if(id == R.id.radioCategory){ currentFilter = FILTER_BY_CATEGORY; }
        });
    }
    private void performSearch(String query){
        switch (currentFilter) {
            case FILTER_BY_NAME: presenter.searchByName(query); break;
            case FILTER_BY_CATEGORY: presenter.searchByCategory(query); break;
            case FILTER_BY_INGREDIENT: presenter.searchByIngredient(query); break;
        }
    }
    private void loadInitialData(){
        presenter.requestCategoryData();
        presenter.requestCountryData();
        presenter.requestIngredientData();
    }

    @Override
    public void getCategoryMeals(List<CategoryMeal> categoryMeals) {
        if(categoryMeals != null){
            getCategoryRcy.setAdapter(categoryAdapter);
            categoryAdapter.updateMeals(categoryMeals);
            categoryAdapter.notifyDataSetChanged();
        }else {
        }

    }

    @Override
    public void getMealsBySearch(List<Meal> meals) {
        if(meals != null){
            searchRcy.setAdapter(bySearchAdapter);
            bySearchAdapter.updateMeals(meals);
            bySearchAdapter.notifyDataSetChanged();
        }else {
        }

    }

    @Override
    public void getIngredients(List<IngredientMeal> ingredients) {
        if (ingredients != null){
            ingredientsRcy.setAdapter(ingredientAdapter);
            ingredientAdapter.updateMeals(ingredients);
            ingredientAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getCountries(List<CountryMeal> country) {
        if(country != null){
            countryRcy.setAdapter(countryAdapter);
            countryAdapter.setCountries(country);
            countryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String errorMsg) {
    }
    private boolean isConnectedToNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}