package com.example.myapplication.home_fragment.view;

import static com.example.myapplication.favorite_fragment.view.FavMealFragment.WHOLE_OBJ;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.example.myapplication.favorite_fragment.view.FavMealFragment;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.home_fragment.presenter.HomeScreenPresenter;

import java.util.List;
import java.util.Random;

public class HomeScreenFragment extends Fragment implements IView {
    ImageView randImg;
    ImageButton hrtIcon, addIcon;
    TextView nameTxt, categoryTxt,countryTxt;
    CardView card;
    boolean isFavorite = false;
    HomeScreenPresenter presenter;
    List<Meal> _Random_meal;
    FavMealFragment favMealFragment;
    Spinner countrySpinner;
    RecyclerView spinnerRcylerView, breakFastRecycler;
    SpinnerItemAdapter spinnerAdapter;
    BreakFastItemAdapter BreakFastAdapter;
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
        setupSpinnerRecyclerView();
        setupBreakFastRecyclerView();
        setupCountrySpinner();
        setupHeartBtn();
        setupCardClick();
        setupAddToCalendarBtn();
        presenter.requestData();
        FragmentManager manager = getParentFragmentManager();
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
        breakFastRecycler = view.findViewById(R.id.BreakfastRecyclerView);
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
        BreakFastAdapter = new BreakFastItemAdapter(getContext(),presenter);
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
                    Toast.makeText(getContext(), "Pich up a meal from "+ selectedCountry+" Kitchen,Enjoy!", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.searchByCountry(countries[random.nextInt(countries.length)]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupHeartBtn(){
        hrtIcon.setOnClickListener(view -> {
                isFavorite = !isFavorite;
                if(! (_Random_meal.isEmpty())){
                    if (isFavorite){
                        hrtIcon.setImageResource(R.drawable.baseline_favorite_24);
                        presenter.addMeal(_Random_meal.get(0));
                        Toast.makeText(getContext(), "The meal is added to favorite", Toast.LENGTH_SHORT).show();
//                    hrtIcon.setColorFilter(getResources().getColor(com.google.android.material.R.color.error_color_material_light));
                    }else{
                        hrtIcon.setImageResource(R.drawable.baseline_favorite_border_24);
                        presenter.rmvMeal(_Random_meal.get(0));
                        Toast.makeText(getContext(), "The meal is removed from favorite", Toast.LENGTH_SHORT).show();
                    }
                }else{ Toast.makeText(getContext(), "No internet Connection", Toast.LENGTH_SHORT).show(); }
        });
    }
    private void setupCardClick(){
        card.setOnClickListener( view ->{
                if(_Random_meal.isEmpty()){
                    Intent outIntent = new Intent(getContext(), DetailsMealActivity.class);
                    outIntent.putExtra(WHOLE_OBJ, _Random_meal.get(0));
                    startActivity(outIntent);
                }else{ Toast.makeText(getContext(), "No internet Connection", Toast.LENGTH_SHORT).show(); }
        });
    }
    private void setupAddToCalendarBtn(){
        addIcon.setOnClickListener(view ->{
                Toast.makeText(getContext(), "Add to Calender", Toast.LENGTH_SHORT).show();
        });
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


}