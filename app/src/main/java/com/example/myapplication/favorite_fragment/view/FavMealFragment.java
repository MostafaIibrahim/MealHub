package com.example.myapplication.favorite_fragment.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.favorite_fragment.presenter.FavMealPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class FavMealFragment extends Fragment implements IFragmentView, OnDeleteMealListener{
    RecyclerView favRcyView;
    FavMealAdapter favAdapter;
    FavMealPresenter presenter;
    View rootView;
    Meal meal;
    LottieAnimationView noMealsAnimationView;
    public final static String WHOLE_OBJ = "Meal_Object";
    public FavMealFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavMealPresenter(MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(getContext()), MealRemoteDataSourceImp.getInstance()),this);
        favRcyView = view.findViewById(R.id.favMealRcyclerView);
        noMealsAnimationView = view.findViewById(R.id.homeNoInternet);
        favRecyclerSetup();
        Observer<List<Meal>> observer = (List<Meal> products) -> {
                if(products.isEmpty() || products == null){
                    noMealsAnimationView.setVisibility(View.VISIBLE);
                    favRcyView.setVisibility(View.GONE);
                }else{
                    noMealsAnimationView.setVisibility(View.GONE);
                    favRcyView.setVisibility(View.VISIBLE);
                    favAdapter.setList(products);
                    favRcyView.setAdapter(favAdapter);
                    favAdapter.notifyDataSetChanged();
                }
        };
        LiveData<List<Meal>> liveData = presenter.getUpdatedData();
        liveData.observe(getViewLifecycleOwner(),observer);


    }
    private void favRecyclerSetup(){
        favRcyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRcyView.setLayoutManager(layoutManager);
        favAdapter = new FavMealAdapter(getContext(),this);
    }
    private void showSnackbarWithAction(View view,  Meal removedMeal) {
        Snackbar snackbar = Snackbar.make(view, "Meal removed", Snackbar.LENGTH_LONG)
                .setAction("UNDO", (View v) -> {
                        presenter.addRequest(removedMeal);
                });
        snackbar.show();
    }

    @Override
    public LiveData<List<Meal>> updateList() {
        return null;
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void onDeleteClickListener(Meal meal) {
        presenter.deleteRequest(meal);
        showSnackbarWithAction(getView(), meal);

    }

    @Override
    public void onDetailsClickListener(Meal meal) {
        if(meal.getStrCategory() != null){
            this.meal = meal;
            Intent outIntent = new Intent(getContext(), DetailsMealActivity.class);
            outIntent.putExtra(WHOLE_OBJ, meal);
            startActivity(outIntent);
        }
        else{
        }
    }
}