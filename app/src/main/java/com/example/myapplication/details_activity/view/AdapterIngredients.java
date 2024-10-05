package com.example.myapplication.details_activity.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.MealHub.R;

import java.util.List;

public class AdapterIngredients extends RecyclerView.Adapter<AdapterIngredients.ViewHolder>{
        private final Context context;
        private List<String> measures , ingredients;
        private static final String TAG = "RecycleView";
        String imgUrl;

    public AdapterIngredients(Context context,List<String> ingredients,List<String> measures) {
        this.context = context;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    @NonNull
    @Override
    public AdapterIngredients.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_ingredient,  parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIngredients.ViewHolder holder, int position) {
        imgUrl = "https://www.themealdb.com/images/ingredients/"+ingredients.get(position)+"-Small.png";
        holder.measure.setText(measures.get(position));
        holder.name.setText(ingredients.get(position));
        Glide.with(context).load(imgUrl).apply(new RequestOptions().override(60, 60))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        if(ingredients != null)
            return ingredients.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail;
        public TextView name , measure;
        public View layout;
        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            name = layout.findViewById(R.id.listCategoryLabel);
            measure = layout.findViewById(R.id.ingredientMeasurement);
            thumbnail = layout.findViewById(R.id.listCategoryThumbnail);
        }

    }
}
