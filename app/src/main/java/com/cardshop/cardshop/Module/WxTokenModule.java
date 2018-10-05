package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.RetrofitService.WxService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class WxTokenModule extends BaseModule {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public static void getToken(String code, Callback<WxTokenModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit(API.WX_BASE_URL);
        WxService service = retrofit.create(WxService.class);
        Call<WxTokenModule> call = service.getWxToken(BaseApplication.APP_ID, BaseApplication.SECRET,
                code, "authorization_code");
        call.enqueue(callback);
    }

    public static void refreshToken(String refreshToken, Callback<WxTokenModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit(API.WX_BASE_URL);
        WxService service = retrofit.create(WxService.class);
        Call<WxTokenModule> call = service.refreshToken(BaseApplication.APP_ID, "refresh_token",
                refreshToken);
        call.enqueue(callback);
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
