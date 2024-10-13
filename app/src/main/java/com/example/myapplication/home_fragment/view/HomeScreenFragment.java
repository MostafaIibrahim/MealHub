package com.example.myapplication.home_fragment.view;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.myapplication.favorite_fragment.view.FavMealFragment.WHOLE_OBJ;
import static com.example.myapplication.home_screen.home_view.HomeScreen.isConnected;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.home_screen.home_view.HomeScreen;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.favorite_fragment.view.FavMealFragment;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.home_fragment.presenter.HomeScreenPresenter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HomeScreenFragment extends Fragment implements IView {
    ImageView randImg;
    ImageButton hrtIcon, addIcon;
    TextView nameTxt, categoryTxt,countryTxt;
    LottieAnimationView noInternetAnimation;
    CardView card;
    boolean isFavorite = false;
    HomeScreenPresenter presenter;
    List<Meal> _Random_meal = null;
    Spinner countrySpinner,mealModeSpinner;
    RecyclerView spinnerRcylerView, breakFastRecycler;
    SpinnerItemAdapter spinnerAdapter;
    MealModetemAdapter mealModeAdapter;
    MaterialDatePicker<Long> datePicker;
    ScrollView scroll;

    public HomeScreenFragment() {
        // Required empty public constructor
        super(R.layout.fragment_random_meal);
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
        presenter = new HomeScreenPresenter(this, MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(getContext()), MealRemoteDataSourceImp.getInstance()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        if(isConnectedToNetwork() != true){
            noInternetAnimation.setVisibility(View.VISIBLE);
            scroll.setVisibility(view.GONE);
        }else{
            noInternetAnimation.setVisibility(View.GONE);
            scroll.setVisibility(view.VISIBLE);
        }
        Calendar clndr = Calendar.getInstance();
        setupSpinnerRecyclerView();
        setupBreakFastRecyclerView();
        setupCountrySpinner();
        setupHeartBtn();
        setupCardClick();
        setupSpinnerMealMode();
        presenter.requestData();
        datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a Date for your Meal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())  // Default selection: today
                .build();

        setupAddToCalendarBtn();
    }
    private void initializeViews(View view){
        nameTxt = view.findViewById(R.id.rmTitle);
        hrtIcon = view.findViewById(R.id.heartIcon);
        randImg = view.findViewById(R.id.rMealThumb);
        categoryTxt = view.findViewById(R.id.categoryTxt);
        countryTxt = view.findViewById(R.id.areaTxt);
        card = view.findViewById(R.id.cardCalendarView);
        addIcon = view.findViewById(R.id.addCalendarBtn);
        countrySpinner = view.findViewById(R.id.countrySpinner);
        spinnerRcylerView = view.findViewById(R.id.spinnerRecyclerView);
        breakFastRecycler = view.findViewById(R.id.breakfastRecyclerView);
        mealModeSpinner = view.findViewById(R.id.mealSpinner);
        noInternetAnimation = view.findViewById(R.id.homeNoInternet);
        scroll = view.findViewById(R.id.scrollView);
    }
    private void setupSpinnerRecyclerView(){
        spinnerRcylerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        spinnerRcylerView.setLayoutManager(layoutManager);
        spinnerAdapter = new SpinnerItemAdapter(getContext(),presenter);
    }
    private void setupBreakFastRecyclerView(){
        breakFastRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        breakFastRecycler.setLayoutManager(layoutManager);
        mealModeAdapter = new MealModetemAdapter(getContext(),presenter);
    }
    private void setupCountrySpinner(){
        Random random = new Random();
        String[] countries = {"Select a country...","American", "British", "Canadian", "Chinese", "Croatian", "Dutch", "Egyptian",
                "Filipino", "French", "Greek", "Indian", "Irish", "Italian", "Jamaican", "Japanese",
                "Kenyan", "Malaysian", "Mexican", "Moroccan", "Polish", "Portuguese", "Russian",
                "Spanish", "Thai", "Tunisian", "Turkish", "Ukrainian", "Vietnamese"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,countries);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        countrySpinner.setAdapter(adapter);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedCountry = adapterView.getItemAtPosition(position).toString();
                if(selectedCountry != "Select a country..."){
                    presenter.searchByCountry(selectedCountry);
                }else{
                    presenter.searchByCountry(countries[random.nextInt(countries.length)]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void setupSpinnerMealMode(){
        String[] spinnerList = {"Select a meal..","Breakfast","Dinner"};
        ArrayAdapter<String> mealModeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,spinnerList);
        mealModeAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        mealModeSpinner.setAdapter(mealModeAdapter);
        mealModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMeal = adapterView.getItemAtPosition(i).toString();
                if (selectedMeal == "Breakfast"){ presenter.searchByCategory("Breakfast"); }
                else if (selectedMeal == "Dinner") {getDinner();}
                else{ presenter.searchByCategory("Starter");}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void setupHeartBtn(){
        hrtIcon.setOnClickListener(view -> {
                isFavorite = !isFavorite;
                if(_Random_meal != null){
                    if (isFavorite){
                        hrtIcon.setImageResource(R.drawable.baseline_favorite_24);
                        presenter.addMeal(_Random_meal.get(0));
//                    hrtIcon.setColorFilter(getResources().getColor(com.google.android.material.R.color.error_color_material_light));
                    }else{
                        hrtIcon.setImageResource(R.drawable.baseline_favorite_border_24);
                        presenter.rmvMeal(_Random_meal.get(0));
                    }
                }else{  }
        });
    }
    private void setupCardClick(){
        card.setOnClickListener( view ->{
                if(_Random_meal != null){
                    Intent outIntent = new Intent(getContext(), DetailsMealActivity.class);
                    outIntent.putExtra(WHOLE_OBJ, _Random_meal.get(0));
                    startActivity(outIntent);
                }else{  }
        });
    }
    private void setupAddToCalendarBtn(){
        addIcon.setOnClickListener(view ->{
                datePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
            });
            //Handle Selected Data
            datePicker.addOnPositiveButtonClickListener(selection -> {
                // Convert the selected date in milliseconds to a readable format
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String selectedDate = sdf.format(new Date(selection));
                if(_Random_meal != null){
                    presenter.insertRequest(DetailsMealActivity.convertToWeeklyPlan(( _Random_meal.get(0))), selectedDate);
                }
        });
    }
    private void getDinner(){
        String[] dinner_categories = {"Beef","Chicken",	"Lamb","Pasta","Seafood","Vegan","Vegetarian"};
        Random random = new Random();
        presenter.searchByCategory(dinner_categories[random.nextInt(dinner_categories.length)]);
    }

    @Override
    public void getRandomMeal(List<Meal> meal) {
        if (meal != null && isAdded()) {
            _Random_meal = meal;
            nameTxt.setText(meal.get(0).getStrMeal());
            categoryTxt.setText(meal.get(0).getStrCategory());
            countryTxt.setText(meal.get(0).getStrArea());
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
    @Override
    public void getCountries(List<Meal> countryMeal) {
        //I will set spinner adapter
        spinnerRcylerView.setAdapter(spinnerAdapter);
        spinnerAdapter.updateMeals(countryMeal);
        spinnerAdapter.notifyDataSetChanged();
    }
    @Override
    public void getBreakFast(List<Meal> breakfastMeals) {
        breakFastRecycler.setAdapter(mealModeAdapter);
        mealModeAdapter.updateMeals(breakfastMeals);
        mealModeAdapter.notifyDataSetChanged();
    }
    private boolean isConnectedToNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}