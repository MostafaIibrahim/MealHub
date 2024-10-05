package com.example.myapplication.calender_fragment.view_calender;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MealHub.R;
import com.example.myapplication.calender_fragment.presenter_calender.CalendarPresenter;
import com.example.myapplication.calender_fragment.presenter_calender.ICalendarPresenter;
import com.example.myapplication.favorite_fragment.view.FavMealFragment;
import com.example.myapplication.home_screen.home_view.home_screen;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.WeeklyMealPlan;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.search_fragment.view.SearchFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalenderSearch extends Fragment implements IViewCalendar {
    RecyclerView calenderRcyView;
    CalenderMealItemAdapter adapter;
    ICalendarPresenter presenter;
    CalendarView clndrView;
    FloatingActionButton fab;
    TextView todayLabel;
    public CalenderSearch() {
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
        return inflater.inflate(R.layout.fragment_calender_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calenderRcyView = view.findViewById(R.id.plannedMealsRecyclerView);
        clndrView = view.findViewById(R.id.calendarView2);
        fab = view.findViewById(R.id.addMealFab);
        todayLabel = view.findViewById(R.id.todayDateLabel);
        todayLabel.setText(getTodayDate());

        presenter = new CalendarPresenter(this,MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance( getContext() ),MealRemoteDataSourceImp.getInstance()));
        setupRecyclerView();
        setupListeners();
    }
    private void setupListeners(){
        clndrView.setOnDateChangeListener( (@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) -> {
            String date = dayOfMonth + "-" + (month+1) + "-" + year;
            presenter.getPlannedMealsOfTheDay(date).observe(getViewLifecycleOwner(),mealList -> {
                if (mealList == null ) {
                    adapter.clearMeals();
                } else {
                    adapter.updateMeals(mealList);  // Update the RecyclerView adapter with new data
                    calenderRcyView.setAdapter(adapter);  // Set adapter to the RecyclerView if needed
                    adapter.notifyDataSetChanged();  // Notify adapter about the data change
                }
            });
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =  new PopupMenu(getContext(),fab);
                popupMenu.getMenuInflater().inflate(R.menu.fab_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_add_from_search) {
                            gotoFrag(new SearchFragment());
                            home_screen.bottomNavigationView.setSelectedItemId(R.id.navSearch);
                        }else if (menuItem.getItemId() == R.id.action_add_from_favorites){
                            gotoFrag(new FavMealFragment());
                            home_screen.bottomNavigationView.setSelectedItemId(R.id.navFav);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private String getTodayDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }
    private void setupRecyclerView(){
        calenderRcyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        calenderRcyView.setLayoutManager(layoutManager);
        adapter = new CalenderMealItemAdapter(getContext(), MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(getContext()), MealRemoteDataSourceImp.getInstance()));
        calenderRcyView.setAdapter(adapter);

    }
    private void gotoFrag(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void getMealsOfDay(List<WeeklyMealPlan> meal) {

    }
}