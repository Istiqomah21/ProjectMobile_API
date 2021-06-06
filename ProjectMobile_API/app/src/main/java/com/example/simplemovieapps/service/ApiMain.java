package com.example.simplemovieapps.service;

import java.util.logging.XMLFormatter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {
    private Retrofit retrofit;

    public MyAPICall getApiQotd(){
        String BASE_URL = "https://favqs.com/";
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MyAPICall.class);
    }

}
