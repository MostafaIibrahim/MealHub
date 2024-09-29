package com.example.myapplication.inspirationmeal.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "meals_table")
public class RandomMeals implements Serializable {
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
   @SerializedName("strIngredient10")
   String strIngredient10;
    @SerializedName("strIngredient11")
   String strIngredient11;
    @SerializedName("strIngredient12")
   String strIngredient12;
    @SerializedName("strIngredient13")
   String strIngredient13;
    @SerializedName("strIngredient14")
   String strIngredient14;
    @SerializedName("strIngredient15")
   String strIngredient15;
    @SerializedName("strIngredient16")
   String strIngredient16;
    @SerializedName("strIngredient17")
   String strIngredient17;
    @SerializedName("strIngredient18")
   String strIngredient18;
    @SerializedName("strIngredient19")
   String strIngredient19;
    @SerializedName("strIngredient20")
   String strIngredient20;

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
    @SerializedName("strMeasure10")
   String strMeasure10;
    @SerializedName("strMeasure11")
     String strMeasure11;
    @SerializedName("strMeasure12")
     String strMeasure12;
    @SerializedName("strMeasure13")
     String strMeasure13;
    @SerializedName("strMeasure14")
     String strMeasure14;
    @SerializedName("strMeasure15")
     String strMeasure15;
    @SerializedName("strMeasure16")
     String strMeasure16;
    @SerializedName("strMeasure17")
     String strMeasure17;
     String strMeasure18;
     String strMeasure19;
     String strMeasure20;
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

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }
    public List<String> getListMeasures() {
        List<String> nonEmptyMeasures = new ArrayList<>();

        // Check each measure and add it to the list if it's not empty
        if (strMeasure1 != null && !strMeasure1.isEmpty()) nonEmptyMeasures.add(strMeasure1);
        if (strMeasure2 != null && !strMeasure2.isEmpty()) nonEmptyMeasures.add(strMeasure2);
        if (strMeasure3 != null && !strMeasure3.isEmpty()) nonEmptyMeasures.add(strMeasure3);
        if (strMeasure4 != null && !strMeasure4.isEmpty()) nonEmptyMeasures.add(strMeasure4);
        if (strMeasure5 != null && !strMeasure5.isEmpty()) nonEmptyMeasures.add(strMeasure5);
        if (strMeasure6 != null && !strMeasure6.isEmpty()) nonEmptyMeasures.add(strMeasure6);
        if (strMeasure7 != null && !strMeasure7.isEmpty()) nonEmptyMeasures.add(strMeasure7);
        if (strMeasure8 != null && !strMeasure8.isEmpty()) nonEmptyMeasures.add(strMeasure8);
        if (strMeasure9 != null && !strMeasure9.isEmpty()) nonEmptyMeasures.add(strMeasure9);
        if (strMeasure10 != null && !strMeasure10.isEmpty()) nonEmptyMeasures.add(strMeasure10);
        if (strMeasure11 != null && !strMeasure11.isEmpty()) nonEmptyMeasures.add(strMeasure11);
        if (strMeasure12 != null && !strMeasure12.isEmpty()) nonEmptyMeasures.add(strMeasure12);
        if (strMeasure13 != null && !strMeasure13.isEmpty()) nonEmptyMeasures.add(strMeasure13);
        if (strMeasure14 != null && !strMeasure14.isEmpty()) nonEmptyMeasures.add(strMeasure14);
        if (strMeasure15 != null && !strMeasure15.isEmpty()) nonEmptyMeasures.add(strMeasure15);
        if (strMeasure16 != null && !strMeasure16.isEmpty()) nonEmptyMeasures.add(strMeasure16);
        if (strMeasure17 != null && !strMeasure17.isEmpty()) nonEmptyMeasures.add(strMeasure17);
        if (strMeasure18 != null && !strMeasure18.isEmpty()) nonEmptyMeasures.add(strMeasure18);
        if (strMeasure19 != null && !strMeasure19.isEmpty()) nonEmptyMeasures.add(strMeasure19);
        if (strMeasure20 != null && !strMeasure20.isEmpty()) nonEmptyMeasures.add(strMeasure20);

        return nonEmptyMeasures;
    }
    public List<String> getListIngredients() {
        List<String> nonEmptyIngredients = new ArrayList<>();

        // Check each ingredient and add it to the list if it's not empty
        if (strIngredient1 != null && !strIngredient1.isEmpty()) nonEmptyIngredients.add(strIngredient1);
        if (strIngredient2 != null && !strIngredient2.isEmpty()) nonEmptyIngredients.add(strIngredient2);
        if (strIngredient3 != null && !strIngredient3.isEmpty()) nonEmptyIngredients.add(strIngredient3);
        if (strIngredient4 != null && !strIngredient4.isEmpty()) nonEmptyIngredients.add(strIngredient4);
        if (strIngredient5 != null && !strIngredient5.isEmpty()) nonEmptyIngredients.add(strIngredient5);
        if (strIngredient6 != null && !strIngredient6.isEmpty()) nonEmptyIngredients.add(strIngredient6);
        if (strIngredient7 != null && !strIngredient7.isEmpty()) nonEmptyIngredients.add(strIngredient7);
        if (strIngredient8 != null && !strIngredient8.isEmpty()) nonEmptyIngredients.add(strIngredient8);
        if (strIngredient9 != null && !strIngredient9.isEmpty()) nonEmptyIngredients.add(strIngredient9);
        if (strIngredient10 != null && !strIngredient10.isEmpty()) nonEmptyIngredients.add(strIngredient10);
        if (strIngredient11 != null && !strIngredient11.isEmpty()) nonEmptyIngredients.add(strIngredient11);
        if (strIngredient12 != null && !strIngredient12.isEmpty()) nonEmptyIngredients.add(strIngredient12);
        if (strIngredient13 != null && !strIngredient13.isEmpty()) nonEmptyIngredients.add(strIngredient13);
        if (strIngredient14 != null && !strIngredient14.isEmpty()) nonEmptyIngredients.add(strIngredient14);
        if (strIngredient15 != null && !strIngredient15.isEmpty()) nonEmptyIngredients.add(strIngredient15);
        if (strIngredient16 != null && !strIngredient16.isEmpty()) nonEmptyIngredients.add(strIngredient16);
        if (strIngredient17 != null && !strIngredient17.isEmpty()) nonEmptyIngredients.add(strIngredient17);
        if (strIngredient18 != null && !strIngredient18.isEmpty()) nonEmptyIngredients.add(strIngredient18);
        if (strIngredient19 != null && !strIngredient19.isEmpty()) nonEmptyIngredients.add(strIngredient19);
        if (strIngredient20 != null && !strIngredient20.isEmpty()) nonEmptyIngredients.add(strIngredient20);

        return nonEmptyIngredients;
    }
}
