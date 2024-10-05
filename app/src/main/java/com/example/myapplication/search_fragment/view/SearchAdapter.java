package com.example.myapplication.search_fragment.view;

import android.content.Context;
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
import com.example.myapplication.model_app.Meal;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private static final String TAG = "search Adapter RecycleView";

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_search_result, parent, false);
        SearchAdapter.ViewHolder vh = new SearchAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).circleCrop()
                .into(holder.thumbnail);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here I want to enter either it was by name or something else I want always to be able to get details

            }
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
