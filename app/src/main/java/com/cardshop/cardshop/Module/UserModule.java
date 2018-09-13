package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.RetrofitService.LoginService;
import com.cardshop.cardshop.Utils.SPUtiles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserModule extends BaseModule {
    private String token;
    private String mid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public static void save(UserModule userModule) {
        SPUtiles.saveUser(userModule);
    }

    public static UserModule getLocalUser() {
        return SPUtiles.getUser();
    }

    public static void login(String userName, String password, Callback<UserModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        LoginService service = retrofit.create(LoginService.class);
        Call<UserModule> userModuleClass = service.login(userName, password);
        userModuleClass.enqueue(callback);
    }
}
