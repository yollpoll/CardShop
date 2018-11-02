package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderDingdanModule;
import com.cardshop.cardshop.Module.OrderModule;
import com.cardshop.cardshop.Module.OrderTihuoModule;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.Module.OrderZhuanmaiModule;

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

    @FormUrlEncoded
    @POST(API.CREATE_ORDER)
    Call<ResponseData<OrderModule>> createOrder(@Field("zhuanmaiId") String zhuanmaiId, @Field("goodsNum") String goodsNum,
                                                @Field("amount") String amount, @Field("memberId") String memberId);


    @FormUrlEncoded
    @POST(API.PAY_BALANCE)
    Call<ResponseData<BaseModule>> payByBalance(@Field("memberId") String memberId, @Field("orderNo") String orderNo,
                                                @Field("payPsw") String payPsw);


    @FormUrlEncoded
    @POST(API.ORDER_YIGOU)
    Call<ResponseData<List<OrderYigouModule>>> getOrderYigou(@Field("memberId") String memberId, @Field("gcName") String gcName,
                                                             @Field("pageNum") String pageNum, @Field("pageSize") String pageSize);

    @FormUrlEncoded
    @POST(API.APPLY_TIHUO)
    Call<ResponseData<BaseModule>> applyGetGoods(@Field("memberId") String memberId, @Field("memberName") String memberName, @Field("memberAvatar") String memberAvatar,
                                                 @Field("goodsId") String goodsId, @Field("goodsName") String goodsName, @Field("goodsNum") String goodsNum,
                                                 @Field("goodsPrice") String goodsPrice, @Field("phone") String phone, @Field("trueName") String trueName,
                                                 @Field("area") String area, @Field("zhuanmaisId") String zhuanmaisId);


    @FormUrlEncoded
    @POST(API.TIHUO_LIST)
    Call<ResponseData<List<OrderTihuoModule>>> getTihuoList(@Field("memberId") String memberId, @Field("gcName") String gcName,
                                                            @Field("pageNum") String pageNum, @Field("pageSize") String pageSize);

    @FormUrlEncoded
    @POST(API.ZHUANMAI_LIST)
    Call<ResponseData<List<OrderZhuanmaiModule>>> getZhuanmaiList(@Field("memberId") String memberId, @Field("cardStatus") String cardStatus,
                                                                  @Field("pageNum") String pageNum, @Field("pageSize") String pageSize);

    @FormUrlEncoded
    @POST(API.GET_ALI_INFO)
    Call<ResponseData<String>> getAliInfo(@Field("payScenes") String payScenes, @Field("memberId") String memberId, @Field("subject") String subject, @Field("body") String body,
                                          @Field("totalFee") String totalFee, @Field("orderNo") String orderNo);
}
