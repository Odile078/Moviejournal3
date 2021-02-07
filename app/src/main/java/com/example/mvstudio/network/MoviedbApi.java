package com.example.mvstudio.network;
//import com.example.movstudio.models.MoviedbPopularResponse;

import com.example.mvstudio.models.MoviedbPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviedbApi {
    @GET("movie/popular?api_key=238710ec527ac19a35ddfec88a113056")
    Call<MoviedbPopularResponse> getMovies();
}
