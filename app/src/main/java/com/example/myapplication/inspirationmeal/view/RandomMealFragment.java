package com.example.myapplication.inspirationmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.favoritemeal.model.MealLocalDataSource;
import com.example.myapplication.favoritemeal.view.FavMealFragment;
import com.example.myapplication.inspirationmeal.Model.RandomMeals;
import com.example.myapplication.inspirationmeal.Model.RandomMealsRemoteDataSource;
import com.example.myapplication.R;
import com.example.myapplication.inspirationmeal.presenter.RandomMealPresenter;

import java.util.List;

public class RandomMealFragment extends Fragment implements IView {
    ImageView randImg;
    Button favBtn;
    TextView nameTxt;
    RandomMealPresenter presenter;
    List<RandomMeals> _meal;
    FavMealFragment favMealFragment;

    public RandomMealFragment() {
        // Required empty public constructor
        super(R.layout.fragment_random_meal);
        System.out.println("I am in Constructor");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random_meal, container, false);
        presenter = new RandomMealPresenter(this, RandomMealsRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(requireContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTxt = view.findViewById(R.id.rmTitle);
        favBtn = view.findViewById(R.id.favBtn);
        randImg = view.findViewById(R.id.rMealThumb);
        FragmentManager manager = getParentFragmentManager();

        presenter.requestData();

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addMeal(_meal.get(0));
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void getRandomMeal(List<RandomMeals> meal) {
        if (meal != null && isAdded()) {
            _meal = meal;
            nameTxt.setText(meal.get(0).getStrMeal());
            Glide.with(getContext())
                    .load(meal.get(0).getStrMealThumb())
                    .apply(new RequestOptions().override(381, 230))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(randImg);
        }
    }

    @Override
    public void showError(String errorMsg) {
        System.out.println("Wrong URD");
    }


}