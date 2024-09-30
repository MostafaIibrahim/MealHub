package com.example.myapplication.search_screen.country_meal.view_country;

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
import com.example.myapplication.model_app.CountryMeal;
import com.example.myapplication.model_app.IngredientMeal;
import com.example.myapplication.search_screen.country_meal.presenter_country.CountryPresenter;
import com.example.myapplication.search_screen.ingredient_meal.view_ingredient.IngredientAdapter;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
private final Context context;
private List<CountryMeal> countries;
private static final String TAG = "Ingredient Adapter RecycleView";
private String imgUrl;
CountryPresenter presenter;

public CountryAdapter(Context context,CountryPresenter presenter) {
    this.presenter=presenter;
    this.context = context;
}

public void setCountries(List<CountryMeal> ingredientMeals) {
    this.countries = ingredientMeals;
}

@NonNull
@Override
public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View v = inflater.inflate(R.layout.area_search_rcycler_adapter, parent, false);
    CountryAdapter.ViewHolder vh = new CountryAdapter.ViewHolder(v);
    return vh;
}

@Override
public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
    String countryFlag = presenter.getCountry(countries.get(position).getStrArea());
    imgUrl = "https://flagcdn.com/160x120/"+ countryFlag+".png";
    holder.name.setText(countries.get(position).getStrArea());
    Glide.with(context).load(imgUrl)
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).circleCrop()
            .into(holder.thumbnail);
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Click Listener is ok from country", Toast.LENGTH_SHORT).show();
        }
    });
}

@Override
public int getItemCount() {
    if (countries != null)
        return countries.size();
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
        linearLayout = layout.findViewById(R.id.item_area);
        name = layout.findViewById(R.id.areaLabel);
        thumbnail = layout.findViewById(R.id.areaThumbnail);
    }

}
}
