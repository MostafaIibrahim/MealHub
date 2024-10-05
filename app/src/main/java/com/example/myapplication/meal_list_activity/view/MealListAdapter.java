package com.example.myapplication.meal_list_activity.view;

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
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.model_app.MealRepository;

import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private static final String TAG = "Category Adapter RecycleView";
    MealRepository repo;
    public static final String MEAL_ID = "meal_id";
    public MealListAdapter(Context context, MealRepository repo) {
        this.context = context;
        this.repo = repo;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_list_meal, parent, false);
        MealListAdapter.ViewHolder vh = new MealListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealListAdapter.ViewHolder holder, int position) {
        System.out.println("I am in adapter" + meals.get(position).getStrMeal());
        holder.mealTitle.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).centerCrop()
                .into(holder.mealImg);
        holder.favBtn.setOnClickListener(view -> {
            Toast.makeText(context, "The meal is added to favorite", Toast.LENGTH_SHORT).show();
            repo.insertMeal(meals.get(position));

        });
        holder.mealCrd.setOnClickListener(view -> {
            Intent toDetails = new Intent(context, DetailsMealActivity.class);
            //I need to send the whole object to the detailed meals
            toDetails.putExtra(MEAL_ID, meals.get(position).getIdMeal());
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
        ImageButton favBtn;
        Button showMoreBtn;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            mealCrd = layout.findViewById(R.id.listMealCard);
            favBtn = layout.findViewById(R.id.favBtn);
            mealTitle = layout.findViewById(R.id.listMealTitle);
            mealImg = layout.findViewById(R.id.imgMealList);
        }

    }
}
/*

 */