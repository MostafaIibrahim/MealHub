package com.example.myapplication.search_screen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.myapplication.model_app.MealPlannerNetworkCallBack;

import java.util.List;


public class SearchFragment extends Fragment implements MealPlannerNetworkCallBack<CategoryMeal> {
    SearchView searchBar;
    RecyclerView categoryRcy, countryRcy,ingredientsRcy;
    TextView seeAll;
    CategoryMealRemoteDataSource src;
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
        categoryRcy = view.findViewById(R.id.searchCategoryRecylerView);
        countryRcy = view.findViewById(R.id.searchCountriesRecylerView);
        ingredientsRcy = view.findViewById(R.id.ingredientsRecyclerView);
        seeAll = view.findViewById(R.id.seeAllIngredients);
        src = CategoryMealRemoteDataSource.getInstance();
        src.makeNetworkCall(this);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "I am see all", Toast.LENGTH_SHORT).show();
            }
        });


        //Now I need to handle adapter of all of these rcyc view and seeall click listener

    }

    @Override
    public void onSuccessful(List<CategoryMeal> meal) {
        Toast.makeText(getContext(), "Category is here and Retro was able to fetch the data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}