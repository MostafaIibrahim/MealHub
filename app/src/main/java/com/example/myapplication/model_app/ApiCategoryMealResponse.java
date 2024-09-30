package com.example.myapplication.model_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiCategoryMealResponse<T> {
   @SerializedName("categories")
   List<T> categories;
}
