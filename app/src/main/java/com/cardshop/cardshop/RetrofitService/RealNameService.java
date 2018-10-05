package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RealNameService {

    @FormUrlEncoded
    @POST(API.REAL_NAME)
    Call<ResponseData<BaseModule>> inputRealName(@Field("memberId") String memberId,
                                                 @Field("idCardNo") String idCardNo,
                                                 @Field("realname") String realname);
}
