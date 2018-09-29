package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AnnouncementModule;
import com.cardshop.cardshop.Module.BannerModule;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.Module.GoodsTypeModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainService {
    @GET(API.BANNER)
    Call<ResponseData<BannerModule>> getBanner();

    @GET(API.ANNOUNCEMENT)
    Call<ResponseData<AnnouncementModule>> getAnnoucement();

    @FormUrlEncoded
    @POST(API.GOODS)
    Call<ResponseData<List<GoodsModule>>> getGoods(@Field("gcName") String gcName, @Field("pageNum") String pageNum,
                                                   @Field("pageSize") String pageSize);

    @GET(API.GOODS_TYPE)
    Call<ResponseData<List<GoodsTypeModule>>> getGoodsType();
}
