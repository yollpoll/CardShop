package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterModule extends BaseModule {
    public static final String CODE_VERTIFY_FAILED = "2";
    public static final String CODE_REGISETERED = "1";

    public static void register(String phone, String password, String captcha, Callback<ResponseData<RegisterModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        RegisterService service = retrofit.create(RegisterService.class);
        Call<ResponseData<RegisterModule>> call = service.register(phone, password, captcha);
        call.enqueue(callback);
    }
}
