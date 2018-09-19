package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Module.FGMsgVertifyModule;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FGMsgService {
    @POST(API.FG_MSG_URL)
    Call<FGMsgVertifyModule> sendMsg(@Query("Account") String account, @Query("Pwd") String pwd
            , @Query("Content") String content, @Query("Mobile") String mobile, @Query("TemplateId") String templateId
            , @Query("SignId") String signId);

}
