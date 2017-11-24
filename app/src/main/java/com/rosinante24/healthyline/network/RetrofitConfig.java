package com.rosinante24.healthyline.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rosinante24 on 22/11/17.
 */

public class RetrofitConfig {
    private static Retrofit getInitRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://api.jakarta.go.id/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServiceApi getInstance() {
        return getInitRetrofit().create(ServiceApi.class);
    }
}
