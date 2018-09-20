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
    private String user_name;
    private String user_pic;
    private String user_phone;
    private String available_predeposit;
    private String card_num;
    private String weixin_nickname;
    private String qq_nickname;


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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getAvailable_predeposit() {
        return available_predeposit;
    }

    public void setAvailable_predeposit(String available_predeposit) {
        this.available_predeposit = available_predeposit;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getWeixin_nickname() {
        return weixin_nickname;
    }

    public void setWeixin_nickname(String weixin_nickname) {
        this.weixin_nickname = weixin_nickname;
    }

    public String getQq_nickname() {
        return qq_nickname;
    }

    public void setQq_nickname(String qq_nickname) {
        this.qq_nickname = qq_nickname;
    }

    public static void login(String userName, String password, Callback<UserModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        LoginService service = retrofit.create(LoginService.class);
        Call<UserModule> userModuleClass = service.login(userName, password);
        userModuleClass.enqueue(callback);
    }

    public static void saveToLocal(UserModule userModule) {
        SPUtiles.saveUser(userModule);
    }

    public static UserModule getCurrentUser() {
        return null != SPUtiles.getUser() ? SPUtiles.getUser() : new UserModule();
    }
}
