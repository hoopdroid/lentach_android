package com.lentach.data.api;

import com.lentach.models.comment.Comment;

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
public class APIManager {

    private static ApiService apiService;
    private static final String URL = "http://93.81.236.83:8090/api";

    public interface ApiService {

        @GET("/getBestComment")
        void getData(Callback<List<Comment>> response);

    }

    public static ApiService getApiService() {

        Executor executor = Executors.newCachedThreadPool();

        RestAdapter restAdapter = new RestAdapter.Builder().
                setExecutors(executor,null).
                setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("RETROFIT_LOG")).
                setEndpoint(URL).build();

        apiService = restAdapter.create(ApiService.class);

        return apiService;


    }

}
