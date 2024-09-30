package com.example.myapplication.search_screen.category_meal.view_category;

import android.content.Context;
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
import com.example.myapplication.model_app.CategoryMeal;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private final Context context;
    private List<CategoryMeal> meals;
    private static final String TAG = "Category Adapter RecycleView";

    public CategoryAdapter(Context context) {
        this.context = context;
    }
    public void updateMeals(List<CategoryMeal> meals){
        this.meals = meals;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_search_recycler_adapter,  parent,false);
        CategoryAdapter.ViewHolder vh = new CategoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrCategory());
        Glide.with(context).load(meals.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).circleCrop()
                .into(holder.thumbnail);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click Listener is ok", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(meals != null)
            return meals.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout linearLayout;
        public ImageView thumbnail;
        public TextView name;
        public View layout;
        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            linearLayout = layout.findViewById(R.id.item_area);
            name = layout.findViewById(R.id.listCategoryLabel);
            thumbnail = layout.findViewById(R.id.listCategoryThumbnail);
        }

    }
}
