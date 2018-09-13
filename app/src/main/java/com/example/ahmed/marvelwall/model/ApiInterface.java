package com.example.ahmed.marvelwall.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<ArrayList<Post>> getPosts();
}
