package com.steven.example.shimmerloadexample.network;

import com.steven.example.shimmerloadexample.model.MovieDetail;
import com.steven.example.shimmerloadexample.model.SearchMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPI {

    @GET("?")
    Call<SearchMovie> searchTitle(@Query("s") String title,
                                  @Query("page") int page,
                                  @Query("apikey") String apiKey);

    @GET("?")
    Call<MovieDetail> getMovieDetail(@Query("i") String id,
                                     @Query("apikey") String apiKey);
}
