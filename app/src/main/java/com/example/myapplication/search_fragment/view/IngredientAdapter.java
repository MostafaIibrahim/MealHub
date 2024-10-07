package com.example.myapplication.search_fragment.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MealHub.R;
import com.example.myapplication.meal_list_activity.view.ListOfMeals;
import com.example.myapplication.model_app.ingreident.IngredientMeal;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private final Context context;
    private List<IngredientMeal> ingredientMeals;
    private static final String TAG = "Ingredient Adapter RecycleView";
    public static final String Ingredient_NAME = "Ingredient Name";
    private String imgUrl;

    public IngredientAdapter(Context context) {
        this.context = context;
    }

    public void updateMeals(List<IngredientMeal> ingredientMeals) {
        this.ingredientMeals = ingredientMeals;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ingredient_search_rcycler_adapter, parent, false);
        IngredientAdapter.ViewHolder vh = new IngredientAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        imgUrl = "https://www.themealdb.com/images/ingredients/" + ingredientMeals.get(position).getStrIngredient() + ".png";
        holder.name.setText(ingredientMeals.get(position).getStrIngredient());
        Glide.with(context).load(imgUrl)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).circleCrop()
                .into(holder.thumbnail);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ingredientMeals != null){
                    Toast.makeText(context, "Explore meals from "+ ingredientMeals.get(position).getStrIngredient() + " ingredient", Toast.LENGTH_SHORT).show();
                    Intent toListCountry = new Intent(context, ListOfMeals.class);
                    toListCountry.putExtra(Ingredient_NAME,ingredientMeals.get(position).getStrIngredient());
                    context.startActivity(toListCountry);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (ingredientMeals != null)
            return ingredientMeals.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public ImageView thumbnail;
        public TextView name;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            linearLayout = layout.findViewById(R.id.breakfastArea);
            name = layout.findViewById(R.id.ingredientLabel);
            thumbnail = layout.findViewById(R.id.ingredientThumbnail);
        }

    }
}

