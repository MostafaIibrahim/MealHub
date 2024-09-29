package com.example.myapplication.home_screen.home_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.MealHub.R;
import com.example.myapplication.details_meal.view.DetailsMealActivity;
import com.example.myapplication.favoritemeal.view.FavMealFragment;
import com.example.myapplication.inspirationmeal.view.RandomMealFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.MealHub.databinding.ActivityHomeScreenBinding;

public class home_screen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;
    private static final String RANDOM_MEAL_FRAGMENT = "RANDOM_MEAL_FRAGMENT";
    RandomMealFragment randomMealFragment;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent Inten = new Intent(home_screen.this, DetailsMealActivity.class);
        replaceFragment(new RandomMealFragment());
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navHome)
                replaceFragment(new RandomMealFragment());
            else if (item.getItemId() == R.id.navFav)
                replaceFragment(new FavMealFragment());
            else if (item.getItemId() == R.id.navSearch)
                startActivity(Inten);
//                replaceFragment(new DetailsMealFragment());

            /*else if (item.getItemId() == R.id.navCalender)
                replaceFragment(new CalenderFragment());*/
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction().setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();

    }
}