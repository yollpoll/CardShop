package com.cardshop.cardshop.Http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpTools {
    private static HttpTools instance = null;
    private static Retrofit retrofit = null;

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API.HEAD_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
//        retrofit.client().interceptors().add(new AddCookieInterceptor());
        return retrofit;
    }
    public static HttpTools getInstance() {
        if (instance == null) {
            instance = new HttpTools();
        }
        return instance;
    }
}
