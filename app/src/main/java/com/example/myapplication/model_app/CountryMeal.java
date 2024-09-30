package com.example.myapplication.model_app;

import java.util.HashMap;
import java.util.Map;

public class CountryMeal {
    private String strArea;
    CountryMeal(String country){
        strArea = country;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

}
