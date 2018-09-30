package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddCardModule;
import com.cardshop.cardshop.Module.CardModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CardService {
    @GET(API.CARDS)
    Call<ResponseData<List<CardModule>>> getCardList(@Query("memberId") String memberId);

    @FormUrlEncoded
    @POST(API.ADD_CARD)
    Call<ResponseData<AddCardModule>> addCard(@Field("memberId") String memberId, @Field("pdcBankUser") String pdcBankUser,
                                              @Field("pdcBankNo") String pdcBankNo, @Field("pdcBankName") String pdcBankName,
                                              @Field("pdcBankAddress") String pdcBankAddress, @Field("idCardNo") String idCardNo,
                                              @Field("phone") String phone);
}
