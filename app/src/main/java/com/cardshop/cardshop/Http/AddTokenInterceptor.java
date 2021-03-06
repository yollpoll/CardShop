package com.cardshop.cardshop.Http;

import com.cardshop.cardshop.Utils.LogUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 鹏祺 on 2017/9/21.
 */

public class AddTokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        Request oldRequest = chain.request();
        Request newRequest = addParam(oldRequest);
        response = chain.proceed(newRequest);

        ResponseBody value = response.body();
        byte[] resp = value.bytes();
        String json = new String(resp, "UTF-8");
        LogUtil.Log("response"+ json);
        // 这里值得注意。由于前面value.bytes()把响应流读完并关闭了，所以这里需要重新生成一个response，否则数据就无法正常解析了
        response = response.newBuilder()
                .body(ResponseBody.create(null, resp))
                .build();
        return response;
    }

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
//                .addQueryParameter("token", SPUtiles.getToken());


        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
//
//        Log.d("spq","token>>>"+SPUtiles.getToken());
//        oldRequest.body();
//        HttpUrl.Builder builder = oldRequest.url()
//                .newBuilder()
//                .addQueryParameter("token",SPUtiles.getToken());
//
//
//        Request newRequest = oldRequest.newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .method(oldRequest.method(), oldRequest.body())
//                .url(builder.build())
//                .build();

        return newRequest;
    }
}
