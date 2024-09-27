package com.example.myapplication.inspirationmeal.Model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class JsonRandomMealsResponse {
   @SerializedName("meals")
   List<RandomMeals> meals;
}