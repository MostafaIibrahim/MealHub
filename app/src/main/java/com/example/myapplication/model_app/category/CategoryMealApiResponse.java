package com.example.myapplication.model_app.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryMealApiResponse<T> {
   @SerializedName("categories")
   public List<T> categories;
}
