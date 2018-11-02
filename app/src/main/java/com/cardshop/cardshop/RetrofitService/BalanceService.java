package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.BalanceModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BalanceService {

    @FormUrlEncoded
    @POST(API.WITHDRAW)
    Call<ResponseData<BaseModule>> withDraw(@Field("memberId") String memberId, @Field("amount") String amount, @Field("pdcId") String pdcId);

    @FormUrlEncoded
    @POST(API.BALANCE_DETAIL_LIST)
    Call<ResponseData<List<BalanceModule>>> getDetailList(@Field("memberId") String memberId, @Field("pageNum") String pageNum, @Field("pageSize") String pageSize);
}
