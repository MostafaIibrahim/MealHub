package com.example.myapplication.model_app;

import java.util.HashMap;
import java.util.Map;

public class CountryMapperModel {
    private Map<String, String> countryMap;
    public CountryMapperModel() {
        countryMap = new HashMap<>();

        countryMap.put("American", "us");
        countryMap.put("British", "gb");
        countryMap.put("Canadian", "ca");
        countryMap.put("Chinese", "cn");
        countryMap.put("Croatian", "hr");
        countryMap.put("Dutch", "nl");
        countryMap.put("Egyptian", "eg");
        countryMap.put("Filipino", "ph");
        countryMap.put("French", "fr");
        countryMap.put("Greek", "gr");
        countryMap.put("Indian", "in");
        countryMap.put("Irish", "ie");
        countryMap.put("Italian", "it");
        countryMap.put("Jamaican", "jm");
        countryMap.put("Japanese", "jp");
        countryMap.put("Kenyan", "ke");
        countryMap.put("Malaysian", "my");
        countryMap.put("Mexican", "mx");
        countryMap.put("Moroccan", "ma");
        countryMap.put("Polish", "pl");
        countryMap.put("Portuguese", "pt");
        countryMap.put("Russian", "ru");
        countryMap.put("Spanish", "es");
        countryMap.put("Thai", "th");
        countryMap.put("Tunisian", "tn");
        countryMap.put("Turkish", "tr");
        countryMap.put("Ukrainian", "ua");
        countryMap.put("Vietnamese", "vn");
    }
    public String getCountryCode(String area) {
        return countryMap.getOrDefault(area, "unknown");
    }
}
