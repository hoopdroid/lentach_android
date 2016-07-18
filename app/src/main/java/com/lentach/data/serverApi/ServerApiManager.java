package com.lentach.data.serverApi;

import com.lentach.models.comment.Comment;
import com.lentach.models.wallpost.Post;
import com.lentach.models.webapipost.WebAPIPost;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.http.GET;

/**
 * A manager that allows get data from network using Retrofit
 */
public class ServerApiManager {

    private static ApiService apiService;
    private static final String URL = "http://93.81.236.83:8090/api";

    public interface ApiService {

        @GET("/getBestComment/?day=1")
        void getBestComments(Callback<List<Comment>> response);

        @GET("/getTopPost/?count=30")
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
