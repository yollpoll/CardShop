package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST(API.VERTIFY_CODE)
    Call<ResponseData<UserModule>> sendMsg(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(API.REGISTER)
    Call<ResponseData<UserModule>> register(@Field("phone") String phone,
                                                @Field("password") String password,
                                                @Field("captcha") String captcha);

    @FormUrlEncoded
    @POST(API.CHANGE_PASSWORD)
    Call<ResponseData<UserModule>> changePassword(@Field("phone") String phone,
                                                  @Field("captcha") String captcha,
                                                  @Field("password") String password);
    @FormUrlEncoded
    @POST(API.LOGIN)
    Call<UserModule> login(@Field("phone") String phone, @Field("password") String pwd);
}
