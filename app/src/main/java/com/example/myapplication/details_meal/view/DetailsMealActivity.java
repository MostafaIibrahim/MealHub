package com.example.myapplication.details_meal.view;
import static com.example.myapplication.inspirationmeal.view.RandomMealFragment.MEAL_OBJECT;
import android.os.Bundle;
import android.view.View;
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
import com.example.myapplication.details_meal.presenter.DetailsPresenter;
import com.example.myapplication.model_app.MealLocalDataSource;
import com.example.myapplication.model_app.RandomMeals;

public class DetailsMealActivity extends AppCompatActivity {
    RandomMeals objMeal;
    ImageView mealPic;
    TextView titleTxt,categoryTxt,areaTxt,stepsTxt;
    RecyclerView rcyc_ingredients;
    AdapterIngredients adapterIngredients;
    WebView youtubeVideo;
    Button favButton;
    DetailsPresenter presenter;
    @Override
    protected void onStart() {
        super.onStart();
        presenter = new DetailsPresenter(MealLocalDataSource.getInstance(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meal);
        objMeal = (RandomMeals) getIntent().getSerializableExtra(MEAL_OBJECT);
        //Get Resource id
        mealPic = findViewById(R.id.detailedMealThumbnail);
        titleTxt = findViewById(R.id.detailedMealName);
        categoryTxt = findViewById(R.id.detailedMealCategory);
        areaTxt = findViewById(R.id.detailedMealArea);
        stepsTxt = findViewById(R.id.mealSteps);
        rcyc_ingredients = findViewById(R.id.ingredientsRecyclerView);
        youtubeVideo = findViewById(R.id.youtubeVideo);
        favButton = findViewById(R.id.btnAddToFavorite);
        //Put these in a function
        displayMealDetails();
        attachToAdapter();
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addToFav(objMeal);
                Toast.makeText(DetailsMealActivity.this, "This meal is added to favorite", Toast.LENGTH_SHORT).show();
            }
        });

    }
    void attachToAdapter(){
        rcyc_ingredients.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcyc_ingredients.setLayoutManager(layoutManager);
        adapterIngredients = new AdapterIngredients(this ,objMeal.getListIngredients(),objMeal.getListMeasures());
        rcyc_ingredients.setAdapter(adapterIngredients);
        adapterIngredients.notifyDataSetChanged();
    }
    void displayMealDetails(){
        Glide.with(getApplicationContext()).load(objMeal.getStrMealThumb()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(mealPic);
        titleTxt.setText(objMeal.getStrMeal());
        categoryTxt.setText(objMeal.getStrCategory());
        areaTxt.setText(objMeal.getStrArea());
        stepsTxt.setText(objMeal.getStrInstructions());
        youtubeVideo.getSettings().setJavaScriptEnabled(true);
        youtubeVideo.setWebViewClient(new WebViewClient());
        String videoUrl = objMeal.getStrYoutube();
        String videoId = videoUrl.substring(videoUrl.lastIndexOf('=') + 1); // Extract the video ID
        String embedUrl = "https://www.youtube.com/embed/" + videoId;
        System.out.println(embedUrl);
        youtubeVideo.loadUrl(embedUrl);
    }
}