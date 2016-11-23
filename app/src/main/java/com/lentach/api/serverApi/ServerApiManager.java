package com.lentach.api.serverApi;

import com.lentach.api.models.comment.Comment;
import com.lentach.api.models.webapipost.WebAPIPost;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * A manager that allows get data from network using Retrofit
 */
public class ServerApiManager {

    private static ApiService apiService;
    private static final String URL = "http://93.81.236.83:8090/api";

    public interface ApiService {

        @GET("/getBestComment/?count=150")
        void getBestComments(Callback<List<Comment>> response);

        @GET("/getPosts?count=200")
        void getBestPosts(Callback<List<WebAPIPost>> response);

    }

    public static ApiService getApiService() {

        Executor executor = Executors.newCachedThreadPool();

        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(URL).build();

        apiService = restAdapter.create(ApiService.class);

        return apiService;


    }

}
