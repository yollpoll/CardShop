package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.MainService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AnnouncementModule extends BaseModule {
    private List<String> home_notice;

    public List<String> getHome_notice() {
        return home_notice;
    }

    public void setHome_notice(List<String> home_notice) {
        this.home_notice = home_notice;
    }

    public static void getModule(Callback<ResponseData<AnnouncementModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseData<AnnouncementModule>> call = service.getAnnoucement();
        call.enqueue(callback);
    }
}
