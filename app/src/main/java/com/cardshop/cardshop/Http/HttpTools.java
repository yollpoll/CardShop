package com.cardshop.cardshop.Http;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpTools {
    private static HttpTools instance = null;
    private static Retrofit retrofit = null;

    public Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API.HEAD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
//        retrofit.client().interceptors().add(new AddCookieInterceptor());
        return retrofit;
    }

    public Retrofit getRetrofit(String baseURl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        retrofit.client().interceptors().add(new AddCookieInterceptor());
        return retrofit;
    }

    public static HttpTools getInstance() {
        if (instance == null) {
            instance = new HttpTools();
        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient() {
        Interceptor interceptor = new AddTokenInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    /**
     * 根据文件创建requestbody
     *
     * @param file
     * @return
     */
    public static RequestBody getRequestBody(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"), file);
        return requestFile;
    }

    public static MultipartBody.Part getPart(String name, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("name", file.getName(), requestFile);
        return body;
    }

    public static boolean ifSuccess(String code) {
        return "0".equals(code);
    }
}
