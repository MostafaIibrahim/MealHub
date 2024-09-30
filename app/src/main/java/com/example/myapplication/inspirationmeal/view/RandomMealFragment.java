package com.example.myapplication.inspirationmeal.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.MealHub.R;
import com.example.myapplication.details_meal.view.DetailsMealActivity;
import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.favoritemeal.view.FavMealFragment;
import com.example.myapplication.model_app.RandomMeal;
import com.example.myapplication.model_app.MealRemoteDataSource;
import com.example.myapplication.inspirationmeal.presenter.RandomMealPresenter;

import java.util.List;

public class RandomMealFragment extends Fragment implements IView {
    public final static String MEAL_OBJECT="Random_Meal";
    ImageView randImg, hrtIcon;
    TextView nameTxt, categoryTxt,countryTxt;
    CardView card;
    boolean isFavorite = false;
    RandomMealPresenter presenter;
    List<RandomMeal> _Random_meal;
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
        presenter = new RandomMealPresenter(this, MealRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(requireContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTxt = view.findViewById(R.id.rmTitle);
        hrtIcon = view.findViewById(R.id.heartIcon);
        randImg = view.findViewById(R.id.rMealThumb);
        categoryTxt = view.findViewById(R.id.categoryTxt);
        countryTxt = view.findViewById(R.id.areaTxt);
        card = view.findViewById(R.id.cardFavView);

        FragmentManager manager = getParentFragmentManager();

        presenter.requestData();

        hrtIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFavorite = !isFavorite;
                if (isFavorite){
                    hrtIcon.setImageResource(R.drawable.baseline_bookmark_added_24);
                    presenter.addMeal(_Random_meal.get(0));
                    Toast.makeText(getContext(), "The meal is added to favorite", Toast.LENGTH_SHORT).show();
//                    hrtIcon.setColorFilter(getResources().getColor(com.google.android.material.R.color.error_color_material_light));
                }else{
                    hrtIcon.setImageResource(R.drawable.baseline_bookmark_border_24);
                    presenter.rmvMeal(_Random_meal.get(0));
                    Toast.makeText(getContext(), "The meal is removed from favorite", Toast.LENGTH_SHORT).show();
                }

            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getContext(), DetailsMealActivity.class);
                outIntent.putExtra(MEAL_OBJECT, _Random_meal.get(0));
                startActivity(outIntent);
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
    public void getRandomMeal(List<RandomMeal> randomMeal) {
        if (randomMeal != null && isAdded()) {
            _Random_meal = randomMeal;
            nameTxt.setText(randomMeal.get(0).getStrMeal());
            categoryTxt.setText(randomMeal.get(0).getStrCategory());
            countryTxt.setText(randomMeal.get(0).getStrArea());
            Glide.with(getContext())
                    .load(randomMeal.get(0).getStrMealThumb())
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