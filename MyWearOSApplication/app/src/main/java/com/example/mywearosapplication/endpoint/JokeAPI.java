package com.example.mywearosapplication.endpoint;

import com.example.mywearosapplication.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JokeAPI {

    @Headers("Accept: application/json")
    @GET("/")
    Call<Joke> getJoke();
}
