package com.example.myapplication.details_activity.view;
import static com.example.myapplication.favorite_fragment.view.FavMealFragment.WHOLE_OBJ;
import static com.example.myapplication.meal_list_activity.view.MealListAdapter.MEAL_ID;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.presenter.DetailsPresenter;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRemoteDataSourceImp;
import com.example.myapplication.model_app.MealRepositoryImp;
import com.example.myapplication.model_app.WeeklyMealPlan;
import com.example.myapplication.model_app.db.MealLocalDataSourceImp;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsMealActivity extends AppCompatActivity implements IViewDetails{
    Meal objMeal;
    String recievedObj;
    ImageView mealPic;
    TextView titleTxt,categoryTxt,areaTxt,stepsTxt;
    RecyclerView rcyc_ingredients;
    AdapterIngredients adapterIngredients;
    WebView youtubeVideo;
    Button favButton , addPlanBtn;
    DetailsPresenter presenter;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meal);
        //Get Resource id
        setComponentsId();
        recievedObj = getIntent().getStringExtra(MEAL_ID);
        presenter = new DetailsPresenter(this ,MealRepositoryImp.getInstance(MealLocalDataSourceImp.getInstance(this), MealRemoteDataSourceImp.getInstance()));
        //I need here to check if the the coming object is by obj name or it's by id
        if (getIntent().hasExtra(WHOLE_OBJ)){
            // then get the obj and assign it to object of meal
            objMeal = (Meal) getIntent().getSerializableExtra(WHOLE_OBJ);
            //call function and send the obj to call display it directly
            displayMealDetails();
        }else if (getIntent().hasExtra(MEAL_ID)){
            recievedObj = getIntent().getStringExtra(MEAL_ID);
            presenter.requestMealById(recievedObj);
            //Then requestMealById which will in return will call callBack and display the received object
        }
        //Put these in a function
        presenter.requestMealById(recievedObj);
        attachToAdapter();

        favButton.setOnClickListener( view -> {
                presenter.addToFav(objMeal);
                Toast.makeText(DetailsMealActivity.this, "This meal is added to favorite", Toast.LENGTH_SHORT).show();
        });
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a Date for your Meal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())  // Default selection: today
                .build();
        //Add listener on the add btn so when it's pressed it shows up the calendar

        addPlanBtn.setOnClickListener( v -> {
            datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        //Handle Selected Data
        datePicker.addOnPositiveButtonClickListener(selection -> {
                // Convert the selected date in milliseconds to a readable format
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String selectedDate = sdf.format(new Date(selection));
            Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();
                //Send the meal and selected data to db
                presenter.insertRequest(convertToWeeklyPlan(objMeal),selectedDate);
            Toast.makeText(this, "Added to Calender db", Toast.LENGTH_SHORT).show();
            });

    }
    void attachToAdapter(){
        rcyc_ingredients.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcyc_ingredients.setLayoutManager(layoutManager);

    }
    void displayMealDetails(){
        Glide.with(getApplicationContext()).load(objMeal.getStrMealThumb()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(mealPic);
        Toast.makeText(this, objMeal.getStrMeal(), Toast.LENGTH_SHORT).show();
        titleTxt.setText(objMeal.getStrMeal());
        categoryTxt.setText(objMeal.getStrCategory());
        areaTxt.setText(objMeal.getStrArea());
        stepsTxt.setText(objMeal.getStrInstructions());
        youtubeVideo.getSettings().setJavaScriptEnabled(true);
        youtubeVideo.setWebViewClient(new WebViewClient());
        String videoUrl = objMeal.getStrYoutube();
        String videoId = videoUrl.substring(videoUrl.lastIndexOf('=') + 1); // Extract the video ID
        String embedUrl = "https://www.youtube.com/embed/" + videoId;
        youtubeVideo.loadUrl(embedUrl);
        attachAdapter();
    }
    private void setComponentsId(){
        mealPic = findViewById(R.id.detailedMealThumbnail);
        titleTxt = findViewById(R.id.detailedMealName);
        categoryTxt = findViewById(R.id.detailedMealCategory);
        areaTxt = findViewById(R.id.detailedMealArea);
        stepsTxt = findViewById(R.id.mealSteps);
        rcyc_ingredients = findViewById(R.id.ingredientsRecyclerView);
        youtubeVideo = findViewById(R.id.youtubeVideo);
        favButton = findViewById(R.id.btnAddToFavorite);
        addPlanBtn = findViewById(R.id.btnAddToPlan);

    }
    void attachAdapter(){
        adapterIngredients = new AdapterIngredients(this , objMeal.getListIngredients(), objMeal.getListMeasures());
        rcyc_ingredients.setAdapter(adapterIngredients);
        adapterIngredients.notifyDataSetChanged();
    }

    @Override
    public void getMealFromRespond(Meal meal) {
        objMeal = meal;
        displayMealDetails();

    }

    @Override
    public void onFailureResult(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public static WeeklyMealPlan convertToWeeklyPlan(Meal objMeal) {
        // Create an instance of WeeklyMealPlan
        WeeklyMealPlan mealPlan = new WeeklyMealPlan();

        // Map the fields from objMeal to mealPlan
        mealPlan.setIdMeal(objMeal.getIdMeal());
        mealPlan.setStrMeal(objMeal.getStrMeal());
        mealPlan.setStrCategory(objMeal.getStrCategory());
        mealPlan.setStrArea(objMeal.getStrArea());
        mealPlan.setStrInstructions(objMeal.getStrInstructions());
        mealPlan.setStrMealThumb(objMeal.getStrMealThumb());
        mealPlan.setStrTags(objMeal.getStrTags());
        mealPlan.setStrYoutube(objMeal.getStrYoutube());

        // Ingredients
        mealPlan.setStrIngredient1(objMeal.getStrIngredient1());
        mealPlan.setStrIngredient2(objMeal.getStrIngredient2());
        mealPlan.setStrIngredient3(objMeal.getStrIngredient3());
        mealPlan.setStrIngredient4(objMeal.getStrIngredient4());
        mealPlan.setStrIngredient5(objMeal.getStrIngredient5());
        mealPlan.setStrIngredient6(objMeal.getStrIngredient6());
        mealPlan.setStrIngredient7(objMeal.getStrIngredient7());
        mealPlan.setStrIngredient8(objMeal.getStrIngredient8());
        mealPlan.setStrIngredient9(objMeal.getStrIngredient9());
        mealPlan.setStrIngredient10(objMeal.getStrIngredient10());
        mealPlan.setStrIngredient11(objMeal.getStrIngredient11());
        mealPlan.setStrIngredient12(objMeal.getStrIngredient12());
        mealPlan.setStrIngredient13(objMeal.getStrIngredient13());
        mealPlan.setStrIngredient14(objMeal.getStrIngredient14());
        mealPlan.setStrIngredient15(objMeal.getStrIngredient15());
        mealPlan.setStrIngredient16(objMeal.getStrIngredient16());
        mealPlan.setStrIngredient17(objMeal.getStrIngredient17());
        mealPlan.setStrIngredient18(objMeal.getStrIngredient18());
        mealPlan.setStrIngredient19(objMeal.getStrIngredient19());
        mealPlan.setStrIngredient20(objMeal.getStrIngredient20());

        // Measurements
        mealPlan.setStrMeasure1(objMeal.getStrMeasure1());
        mealPlan.setStrMeasure2(objMeal.getStrMeasure2());
        mealPlan.setStrMeasure3(objMeal.getStrMeasure3());
        mealPlan.setStrMeasure4(objMeal.getStrMeasure4());
        mealPlan.setStrMeasure5(objMeal.getStrMeasure5());
        mealPlan.setStrMeasure6(objMeal.getStrMeasure6());
        mealPlan.setStrMeasure7(objMeal.getStrMeasure7());
        mealPlan.setStrMeasure8(objMeal.getStrMeasure8());
        mealPlan.setStrMeasure9(objMeal.getStrMeasure9());
        mealPlan.setStrMeasure10(objMeal.getStrMeasure10());
        mealPlan.setStrMeasure11(objMeal.getStrMeasure11());
        mealPlan.setStrMeasure12(objMeal.getStrMeasure12());
        mealPlan.setStrMeasure13(objMeal.getStrMeasure13());
        mealPlan.setStrMeasure14(objMeal.getStrMeasure14());
        mealPlan.setStrMeasure15(objMeal.getStrMeasure15());
        mealPlan.setStrMeasure16(objMeal.getStrMeasure16());
        mealPlan.setStrMeasure17(objMeal.getStrMeasure17());
        mealPlan.setStrMeasure18(objMeal.getStrMeasure18());
        mealPlan.setStrMeasure19(objMeal.getStrMeasure19());
        mealPlan.setStrMeasure20(objMeal.getStrMeasure20());


        // Return the converted WeeklyMealPlan object
        return mealPlan;
    }
}