package com.example.myapplication.inspirationmeal.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meals_table")
public class RandomMeals {
    @PrimaryKey
    @NonNull
   @SerializedName("idMeal")
   String idMeal;

   @SerializedName("strMeal")
   String strMeal;

   @SerializedName("strCategory")
   String strCategory;

   @SerializedName("strArea")
   String strArea;
   @SerializedName("strInstructions")
   String strInstructions;

   @SerializedName("strMealThumb")
   String strMealThumb;

   @SerializedName("strTags")
   String strTags;

   @SerializedName("strYoutube")
   String strYoutube;

   @SerializedName("strIngredient1")
   String strIngredient1;

   @SerializedName("strIngredient2")
   String strIngredient2;

   @SerializedName("strIngredient3")
   String strIngredient3;

   @SerializedName("strIngredient4")
   String strIngredient4;

   @SerializedName("strIngredient5")
   String strIngredient5;

   @SerializedName("strIngredient6")
   String strIngredient6;

   @SerializedName("strIngredient7")
   String strIngredient7;

   @SerializedName("strIngredient8")
   String strIngredient8;

   @SerializedName("strIngredient9")
   String strIngredient9;

   @SerializedName("strMeasure1")
   String strMeasure1;

   @SerializedName("strMeasure2")
   String strMeasure2;

   @SerializedName("strMeasure3")
   String strMeasure3;

   @SerializedName("strMeasure4")
   String strMeasure4;

   @SerializedName("strMeasure5")
   String strMeasure5;

   @SerializedName("strMeasure6")
   String strMeasure6;

   @SerializedName("strMeasure7")
   String strMeasure7;

   @SerializedName("strMeasure8")
   String strMeasure8;

   @SerializedName("strMeasure9")
   String strMeasure9;

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
    public String getIdMeal() {
        return idMeal;
    }
    
    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }
    public String getStrMeal() {
        return strMeal;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
    public String getStrCategory() {
        return strCategory;
    }
    
    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
    public String getStrArea() {
        return strArea;
    }
    
    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }
    public String getStrInstructions() {
        return strInstructions;
    }
    
    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
    public String getStrMealThumb() {
        return strMealThumb;
    }
    
    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }
    public String getStrTags() {
        return strTags;
    }
    
    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }
    public String getStrYoutube() {
        return strYoutube;
    }
    
    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }
    public String getStrIngredient1() {
        return strIngredient1;
    }
    
    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }
    public String getStrIngredient2() {
        return strIngredient2;
    }
    
    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }
    public String getStrIngredient3() {
        return strIngredient3;
    }
    
    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }
    public String getStrIngredient4() {
        return strIngredient4;
    }
    
    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }
    public String getStrIngredient5() {
        return strIngredient5;
    }
    
    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }
    public String getStrIngredient6() {
        return strIngredient6;
    }
    
    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }
    public String getStrIngredient7() {
        return strIngredient7;
    }
    
    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }
    public String getStrIngredient8() {
        return strIngredient8;
    }
    
    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }
    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }
    public String getStrMeasure1() {
        return strMeasure1;
    }
    
    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }
    public String getStrMeasure2() {
        return strMeasure2;
    }
    
    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }
    public String getStrMeasure3() {
        return strMeasure3;
    }
    
    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }
    public String getStrMeasure4() {
        return strMeasure4;
    }
    
    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }
    public String getStrMeasure5() {
        return strMeasure5;
    }
    
    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }
    public String getStrMeasure6() {
        return strMeasure6;
    }
    
    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }
    public String getStrMeasure7() {
        return strMeasure7;
    }
    
    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }
    public String getStrMeasure8() {
        return strMeasure8;
    }
    
    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }
    public String getStrMeasure9() {
        return strMeasure9;
    }
    

}