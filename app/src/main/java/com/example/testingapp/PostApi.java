package com.example.testingapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApi {

    @GET("/posts")
    Call<List<MyList>> getAllPosts();

    @GET("/posts/{id}")
    Call<MyList> GET(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("/posts")
    Call<MyList> POST(@Body MyList object);



}
