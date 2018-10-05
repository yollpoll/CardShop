package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.RealNameService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RealNameModule extends BaseModule {
    private String name;
    private String identityCode;


    public static void inputRealName(String memberId, String name, String identityCode,
                                     Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        RealNameService service = retrofit.create(RealNameService.class);
        Call<ResponseData<BaseModule>> call = service.inputRealName(memberId, identityCode, name);
        call.enqueue(callback);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
}
