package com.example.myapplication.favoritemeal.view;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.example.MealHub.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.model_app.RandomMeal;

import java.util.List;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.ViewHolder> {
    private final Context context;
    private List<RandomMeal> values;
    private static final String TAG = "RecycleView";
    OnDeleteMealListener listener;
    public FavMealAdapter(Context m_context ,OnDeleteMealListener listener){
        context = m_context;
        this.listener = listener;
    }
    public void setList(List<RandomMeal> updatedList){
        values = updatedList;
    }

    @NonNull
    @Override
    public FavMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_rcy_fav,  parent,false);
        FavMealAdapter.ViewHolder vh = new FavMealAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealAdapter.ViewHolder holder, int position) {
        holder.name.setText(values.get(position).getStrMeal());
        holder.description.setText(values.get(position).getStrArea());
        Glide.with(context).load(values.get(position).getStrMealThumb()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteClickListener(values.get(position));
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { listener.onDetailsClickListener(values.get(position));}
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail;
        public TextView name, description;
        public CardView card;
        public View layout;
        public Button delete;
        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            description = layout.findViewById(R.id.favDescriptionTxt);
            name = layout.findViewById(R.id.favTitleTxt);
            thumbnail = layout.findViewById(R.id.favThumb);
            delete = layout.findViewById(R.id.rmvBtn);
            card = layout.findViewById(R.id.cardFavView);
        }
    }
}
