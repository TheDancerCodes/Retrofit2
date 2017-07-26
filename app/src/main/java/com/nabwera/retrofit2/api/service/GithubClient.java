package com.nabwera.retrofit2.api.service;

import com.nabwera.retrofit2.api.model.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nabwera on 25/07/2017.
 */

public interface GithubClient {

    // illustrates the usage of Retrofit’s path parameter replacement functionality.
    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}