package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.MsgCodeModule;
import com.cardshop.cardshop.Module.RegisterModule;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterService {
    @FormUrlEncoded
    @POST(API.VERTIFY_CODE)
    Call<ResponseData<MsgCodeModule>> sendMsg(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(API.REGISTER)
    Call<ResponseData<RegisterModule>> register(@Field("phone") String phone,
                                                @Field("password") String password,
                                                @Field("captcha") String captcha);
}
