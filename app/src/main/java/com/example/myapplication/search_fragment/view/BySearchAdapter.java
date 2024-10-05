package com.example.myapplication.search_fragment.view;

import static com.example.myapplication.meal_list_activity.view.MealListAdapter.MEAL_ID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MealHub.R;
import com.example.myapplication.details_activity.view.DetailsMealActivity;
import com.example.myapplication.model_app.Meal;
import com.example.myapplication.search_fragment.presenter.SearchPresenter;

import java.util.List;

public class BySearchAdapter extends RecyclerView.Adapter<BySearchAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private static final String TAG = "search Adapter RecycleView";
    SearchPresenter presenter;
    public BySearchAdapter(Context context , SearchPresenter presenter) {
        this.presenter = presenter;
        this.context = context;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public BySearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_search_result, parent, false);
        BySearchAdapter.ViewHolder vh = new BySearchAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BySearchAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).centerCrop()
                .into(holder.thumbnail);
        holder.card.setOnClickListener((view) -> {
            Intent toDetails = new Intent(context, DetailsMealActivity.class);
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
        public CardView card;
        public ImageView thumbnail;
        public TextView name;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            card = layout.findViewById(R.id.cardCalendarView);
            name = layout.findViewById(R.id.calendarTitleTxt);
            thumbnail = layout.findViewById(R.id.calendarThumb);
        }

    }
}
