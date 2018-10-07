package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderDingdanModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderService {
    @FormUrlEncoded
    @POST(API.ORDER_DINGDAN)
    Call<ResponseData<List<OrderDingdanModule>>> getOrderDingdan(@Field("memberId") String memberId,
                                                               @Field("pageNum") String pageNum,
                                                               @Field("pageSize") String pageSize);
}
