package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @FormUrlEncoded
    @POST(API.VERTIFY_CODE)
    Call<ResponseData<BaseModule>> sendMsg(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(API.REGISTER)
    Call<ResponseData<UserModule>> register(@Field("phone") String phone,
                                            @Field("password") String password,
                                            @Field("smsCode") String smsCode,
                                            @Field("openId") String openId);

    @FormUrlEncoded
    @POST(API.CHANGE_PASSWORD)
    Call<ResponseData<UserModule>> changePassword(@Field("phone") String phone,
                                                  @Field("smsCode") String smsCode,
                                                  @Field("newPsw") String password);

    @FormUrlEncoded
    @POST(API.LOGIN)
    Call<ResponseData<UserModule>> login(@Field("phone") String phone, @Field("password") String pwd);

    @FormUrlEncoded
    @POST(API.VERTIFY_SMS)
    Call<ResponseData<BaseModule>> vertifySms(@Field("phone") String phone, @Field("smsCode") String smsCode);

    @FormUrlEncoded
    @POST(API.AUTH_LOGIN)
    Call<ResponseData<UserModule>> authLogin(@Field("openId") String openId, @Field("nickName") String nickName,
                                             @Field("headImg") String headImg, @Field("authType") String authType);


    @FormUrlEncoded
    @POST(API.CHANGE_NAME)
    Call<ResponseData<BaseModule>> changeName(@Field("memberId") String memberId, @Field("memberName") String memberName);


    @GET(API.USER_INFO)
    Call<ResponseData<UserModule>> getUserInfo(@Query("memberId") String memberId);
}
