package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.RetrofitService.WxService;
import com.cardshop.cardshop.Utils.SPUtiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class WxUserInfoModule extends BaseModule {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private List<String> privilege;
    private String unionid;

    public static void getWxUserInfo(String token, String openId, Callback<WxUserInfoModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit(API.WX_BASE_URL);
        WxService service = retrofit.create(WxService.class);
        Call<WxUserInfoModule> call = service.getUserInfo(token, openId);
        call.enqueue(callback);
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public static void saveToLocal(WxUserInfoModule userModule) {
        SPUtiles.saveWxUser(userModule);
    }

    public static WxUserInfoModule getLocalUserInfo() {
        return SPUtiles.getWxUser();
    }

    public static void clearLocalInfo() {
        SPUtiles.saveWxUser(null);
    }
}
