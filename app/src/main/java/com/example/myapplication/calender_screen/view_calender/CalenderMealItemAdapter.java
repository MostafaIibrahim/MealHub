package com.example.myapplication.calender_screen.view_calender;

import static com.example.myapplication.favoritemeal.view.FavMealFragment.WHOLE_OBJ;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MealHub.R;
import com.example.myapplication.details_meal.view.DetailsMealActivity;
import com.example.myapplication.meal_list_activity.view.MealListAdapter;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;
import com.example.myapplication.model_app.WeeklyMealPlan;

import java.util.List;

public class CalenderMealItemAdapter extends RecyclerView.Adapter<CalenderMealItemAdapter.ViewHolder> {
    private final Context context;
    private List<WeeklyMealPlan> meals;
    private static final String TAG = "Day Meal Card Adapter RecycleView";
    MealRepository repo;
    public static final String MEAL_ID = "meal_id";
    public CalenderMealItemAdapter(Context context, MealRepository repo) {
        this.context = context;
        this.repo = repo;
    }

    public void updateMeals(List<WeeklyMealPlan> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public CalenderMealItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.day_item, parent, false);
        CalenderMealItemAdapter.ViewHolder vh = new CalenderMealItemAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderMealItemAdapter.ViewHolder holder, int position) {
        holder.mealTitle.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).centerCrop()
                .into(holder.mealImg);
        holder.rmvBtn.setOnClickListener(view -> {
            Toast.makeText(context, "The meal is deleted from Calender", Toast.LENGTH_SHORT).show();
            repo.deletePlannedMeal(meals.get(position));
        });
        holder.mealCrd.setOnClickListener(view -> {
            Intent toDetails = new Intent(context, DetailsMealActivity.class);
            //I need to send the whole object to the detailed meals
            toDetails.putExtra(WHOLE_OBJ, convertToMeal(meals.get(position)));
            context.startActivity(toDetails);
        });
    }

    @Override
    public int getItemCount() {
        if (meals != null)
            return meals.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mealCrd;
        ImageView mealImg;
        TextView mealTitle;
        Button rmvBtn;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            mealCrd = layout.findViewById(R.id.cardCalendarView);
            rmvBtn = layout.findViewById(R.id.calendarRmvBtn);
            mealTitle = layout.findViewById(R.id.calendarTitleTxt);
            mealImg = layout.findViewById(R.id.calendarThumb);
        }

    }

    public Meal convertToMeal(WeeklyMealPlan weeklyMealPlan) {
        // Create an instance of Meal
        Meal meal = new Meal();

        // Map the fields from weeklyMealPlan to meal
        meal.setIdMeal(weeklyMealPlan.getIdMeal());
        meal.setStrMeal(weeklyMealPlan.getStrMeal());
        meal.setStrCategory(weeklyMealPlan.getStrCategory());
        meal.setStrArea(weeklyMealPlan.getStrArea());
        meal.setStrInstructions(weeklyMealPlan.getStrInstructions());
        meal.setStrMealThumb(weeklyMealPlan.getStrMealThumb());
        meal.setStrTags(weeklyMealPlan.getStrTags());
        meal.setStrYoutube(weeklyMealPlan.getStrYoutube());

        // Ingredients
        meal.setStrIngredient1(weeklyMealPlan.getStrIngredient1());
        meal.setStrIngredient2(weeklyMealPlan.getStrIngredient2());
        meal.setStrIngredient3(weeklyMealPlan.getStrIngredient3());
        meal.setStrIngredient4(weeklyMealPlan.getStrIngredient4());
        meal.setStrIngredient5(weeklyMealPlan.getStrIngredient5());
        meal.setStrIngredient6(weeklyMealPlan.getStrIngredient6());
        meal.setStrIngredient7(weeklyMealPlan.getStrIngredient7());
        meal.setStrIngredient8(weeklyMealPlan.getStrIngredient8());
        meal.setStrIngredient9(weeklyMealPlan.getStrIngredient9());
        meal.setStrIngredient10(weeklyMealPlan.getStrIngredient10());
        meal.setStrIngredient11(weeklyMealPlan.getStrIngredient11());
        meal.setStrIngredient12(weeklyMealPlan.getStrIngredient12());
        meal.setStrIngredient13(weeklyMealPlan.getStrIngredient13());
        meal.setStrIngredient14(weeklyMealPlan.getStrIngredient14());
        meal.setStrIngredient15(weeklyMealPlan.getStrIngredient15());
        meal.setStrIngredient16(weeklyMealPlan.getStrIngredient16());
        meal.setStrIngredient17(weeklyMealPlan.getStrIngredient17());
        meal.setStrIngredient18(weeklyMealPlan.getStrIngredient18());
        meal.setStrIngredient19(weeklyMealPlan.getStrIngredient19());
        meal.setStrIngredient20(weeklyMealPlan.getStrIngredient20());

        // Measurements
        meal.setStrMeasure1(weeklyMealPlan.getStrMeasure1());
        meal.setStrMeasure2(weeklyMealPlan.getStrMeasure2());
        meal.setStrMeasure3(weeklyMealPlan.getStrMeasure3());
        meal.setStrMeasure4(weeklyMealPlan.getStrMeasure4());
        meal.setStrMeasure5(weeklyMealPlan.getStrMeasure5());
        meal.setStrMeasure6(weeklyMealPlan.getStrMeasure6());
        meal.setStrMeasure7(weeklyMealPlan.getStrMeasure7());
        meal.setStrMeasure8(weeklyMealPlan.getStrMeasure8());
        meal.setStrMeasure9(weeklyMealPlan.getStrMeasure9());
        meal.setStrMeasure10(weeklyMealPlan.getStrMeasure10());
        meal.setStrMeasure11(weeklyMealPlan.getStrMeasure11());
        meal.setStrMeasure12(weeklyMealPlan.getStrMeasure12());
        meal.setStrMeasure13(weeklyMealPlan.getStrMeasure13());
        meal.setStrMeasure14(weeklyMealPlan.getStrMeasure14());
        meal.setStrMeasure15(weeklyMealPlan.getStrMeasure15());
        meal.setStrMeasure16(weeklyMealPlan.getStrMeasure16());
        meal.setStrMeasure17(weeklyMealPlan.getStrMeasure17());
        meal.setStrMeasure18(weeklyMealPlan.getStrMeasure18());
        meal.setStrMeasure19(weeklyMealPlan.getStrMeasure19());
        meal.setStrMeasure20(weeklyMealPlan.getStrMeasure20());

        // Return the converted Meal object
        return meal;
    }

}
