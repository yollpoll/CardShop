package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.MainService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class BannerModule extends BaseModule {
    private List<String> banner;

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public static void getBannerModule(Callback<ResponseData<BannerModule>> callBack) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseData<BannerModule>> call = service.getBanner();
        call.enqueue(callBack);
    }

}
