package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.WxTokenModule;
import com.cardshop.cardshop.Module.WxUserInfoModule;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WxService {
    @GET(API.WX_GET_TOKEN)
    Call<WxTokenModule> getWxToken(@Query("appid") String appid, @Query("secret") String secret,
                                   @Query("code") String code, @Query("grant_type") String grant_type);

    @GET(API.WX_REFRESH_TOKEN)
    Call<WxTokenModule> refreshToken(@Query("appid") String appid, @Query("grant_type") String grant_type,
                                     @Query("refresh_token") String refresh_token);

    @GET(API.WX_USER_INFO)
    Call<WxUserInfoModule> getUserInfo(@Query("access_token") String access_token, @Query("openid") String openid);

    @FormUrlEncoded
    @POST(API.WX_BIND)
    Call<ResponseData<BaseModule>> bindWx(@Field("openId") String openId, @Field("nickName") String nickName, @Field("headImg") String headImg,
                                          @Field("memberId") String memberId);
}
