package com.example.myapplication.home_fragment.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.home_fragment.presenter.HomeScreenPresenter;
import com.example.myapplication.model_app.Meal;

import java.util.List;

public class BreakFastItemAdapter extends RecyclerView.Adapter<BreakFastItemAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private static final String TAG = "Category Adapter RecycleView";
    HomeScreenPresenter presenter;
    public static final String MEAL_ID = "meal_id";
    public BreakFastItemAdapter(Context context, HomeScreenPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public BreakFastItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_breakfast_item, parent, false);
        BreakFastItemAdapter.ViewHolder vh = new BreakFastItemAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BreakFastItemAdapter.ViewHolder holder, int position) {
        holder.mealTitle.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).circleCrop()
                .into(holder.mealImg);
        holder.mealImg.setOnClickListener(view -> {
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
        ImageView mealImg;
        TextView mealTitle;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            mealTitle = layout.findViewById(R.id.breafastTitle);
            mealImg = layout.findViewById(R.id.breakFastThumbnail);
        }

    }
}
