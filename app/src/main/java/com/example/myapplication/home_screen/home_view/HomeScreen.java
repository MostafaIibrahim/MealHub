package com.example.myapplication.home_screen.home_view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

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

public class HomeScreen extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Button connect;
    public static Boolean isConnected;
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
            replaceFragment(new HomeScreenFragment());
            bottomNavigationView.setSelectedItemId(R.id.navHome);
            isConnected = true;
        } else {
            replaceFragment(new FavMealFragment());
            bottomNavigationView.setSelectedItemId(R.id.navFav);
            isConnected = false;
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

    @Override
    protected void onStart() {
        super.onStart();
        if(isConnectedToNetwork()){
            isConnected = true;
        }else isConnected = false;
    }

    private boolean isConnectedToNetwork(){
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