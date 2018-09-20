package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MsgCodeModule extends BaseModule {
    public static void getMsgCode(String phone, Callback<ResponseData<MsgCodeModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        RegisterService service = retrofit.create(RegisterService.class);
        Call<ResponseData<MsgCodeModule>> call = service.sendMsg(phone);
        call.enqueue(callback);
    }

}
