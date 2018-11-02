package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.MainService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class BannerAndAnnouncementModule extends BaseModule {

    /**
     * banner : ["http://www.s.kinfinger.com/wap/images/banner1.jpg","http://www.s.kinfinger.com/wap/images/banner2.jpg","http://www.s.kinfinger.com/wap/images/banner3.jpg"]
     * bulletion : 欢迎来到卡卡淘淘商城!
     */

    private String bulletion;
    private List<String> banner;

    public String getBulletion() {
        return bulletion;
    }

    public void setBulletion(String bulletion) {
        this.bulletion = bulletion;
    }

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public static void getBannerAndAnnouncement(Callback<ResponseData<BannerAndAnnouncementModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        service.getBannerAneAnnouncement().enqueue(callback);
    }
}
