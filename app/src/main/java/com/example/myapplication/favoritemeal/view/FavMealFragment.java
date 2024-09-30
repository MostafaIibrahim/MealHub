package com.example.myapplication.favoritemeal.view;

import static com.example.myapplication.inspirationmeal.view.RandomMealFragment.MEAL_OBJECT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.MealHub.R;
import com.example.myapplication.details_meal.view.DetailsMealActivity;
import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.favoritemeal.presenter.FavMealPresenter;
import com.example.myapplication.model_app.RandomMeals;

import java.util.List;


public class FavMealFragment extends Fragment implements IFragmentView, OnDeleteMealListener{
    RecyclerView favRcyView;
    FavMealAdapter favAdapter;
    FavMealPresenter presenter;
    CardView card;
    public FavMealFragment() {
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
        return inflater.inflate(R.layout.fragment_fav_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavMealPresenter(MealLocalDataSource.getInstance(getContext()),this);
        favRcyView = view.findViewById(R.id.favMealRcyclerView);
        favRcyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRcyView.setLayoutManager(layoutManager);
        favAdapter = new FavMealAdapter(getContext(),this);

        Observer<List<RandomMeals>> observer = new Observer<List<RandomMeals>>() {
            @Override
            public void onChanged(List<RandomMeals> products) {
                favAdapter.setList(products);
                favRcyView.setAdapter(favAdapter);
                favAdapter.notifyDataSetChanged();
            }
        };
        LiveData<List<RandomMeals>> liveData = presenter.getUpdatedData();
        liveData.observe(getViewLifecycleOwner(),observer);


    }

    @Override
    public LiveData<List<RandomMeals>> updateList() {
        return null;
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void onDeleteClickListener(RandomMeals meal) {
        presenter.deleteRequest(meal);

    }

    @Override
    public void onDetailsClickListener(RandomMeals meal) {
        Intent outIntent = new Intent(getContext(), DetailsMealActivity.class);
        outIntent.putExtra(MEAL_OBJECT,meal);
        startActivity(outIntent);
    }
}