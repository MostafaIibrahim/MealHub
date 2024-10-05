package com.example.myapplication.home_screen.home_view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.MealHub.R;
import com.example.myapplication.calender_fragment.view_calender.CalenderSearch;
import com.example.myapplication.favorite_fragment.view.FavMealFragment;
import com.example.myapplication.home_fragment.view.HomeScreenFragment;
import com.example.myapplication.search_fragment.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.MealHub.databinding.ActivityHomeScreenBinding;

public class home_screen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Button connect;
    ActivityHomeScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.fragmentContainerView);

        checkNetworkAndInitialize();
        setupBottomNavigationView();


    }
    private void checkNetworkAndInitialize(){
        if (isConnectedToNetwork()) {
            Toast.makeText(this, "Connected to Network", Toast.LENGTH_SHORT).show();
            replaceFragment(new HomeScreenFragment());
        } else {
            Toast.makeText(this, "You need to connect to Network", Toast.LENGTH_SHORT).show();
            replaceFragment(new FavMealFragment());
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction().setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();
    }
    private void setupBottomNavigationView(){
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navHome)
                replaceFragment(new HomeScreenFragment());
            else if (item.getItemId() == R.id.navFav)
                replaceFragment(new FavMealFragment());
            else if (item.getItemId() == R.id.navSearch)
                replaceFragment(new SearchFragment());
            else if (item.getItemId() == R.id.navCalender)
                replaceFragment(new CalenderSearch());
            return true;
        });
    }
    boolean isConnectedToNetwork(){
        boolean state = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            if(networkInfo.isConnected()){
                state = true;
            }else
                state = false;
        }
        return  state;
    }
}