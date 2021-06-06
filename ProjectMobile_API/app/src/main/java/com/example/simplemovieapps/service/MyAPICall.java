package com.example.simplemovieapps.service;

import com.example.simplemovieapps.model.QuoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {

    @GET("api/qotd")
    Call<QuoteResponse> getData();
}
