package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AnnouncementModule;
import com.cardshop.cardshop.Module.BannerModule;
import com.cardshop.cardshop.Module.GoodsModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainService {
    @GET(API.BANNER)
    Call<ResponseData<BannerModule>> getBanner();

    @GET(API.ANNOUNCEMENT)
    Call<ResponseData<AnnouncementModule>> getAnnoucement();

    @GET(API.GOODS)
    Call<ResponseData<GoodsModule>> getGoods(@Query("status") int status);
}
