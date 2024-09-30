package com.example.myapplication.model_app;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class ApiMealResponse<T>{
   @SerializedName("meals")
   List<T> meals;
}
