package com.cardshop.cardshop.Utils;


import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Module.WxUserInfoModule;
import com.cardshop.framework.Utils.SharePreferencesUtils;
import com.google.gson.Gson;

/**
 * Created by 鹏祺 on 2017/5/24.
 */

public class SPUtiles {
    //    public static final String TOKEN = "token";
    public static final String USER = "user";
    public static final String LOGIN_PHONE = "login_phone";
    public static final String GESTURE = "gesture";
    public static final String IS_OPEN_GESTURE = "is_open_gesture";
    public static final String MD5_PSW = "md5_psw";

    public static final String IS_LOGIN_VIA_WX = "is_login_via_wx";
    public static final String WX_USER = "wx_user";


    //user会空的情况下使用这个
    public static String getSPKey(String key) {
        if (null != getLoginPhone())
            return getLoginPhone() + key;
        return key;
    }

    //user不为空的情况下
    public static String getIdKey(String key) {
        if (null != getUser())
            return getUser().getMember().getMemberId() + key;
        return key;
    }

    public static String getLoginPhone() {
        return SharePreferencesUtils.getString(LOGIN_PHONE);
    }

    public static void saveLoginPhone(String phone) {
        SharePreferencesUtils.putString(LOGIN_PHONE, phone);
    }

    public static UserModule getUser() {
        String json = SharePreferencesUtils.getString(getSPKey(USER));
        Gson gson = new Gson();
        UserModule userBean = gson.fromJson(json, UserModule.class);
        return userBean;
    }

    public static void saveUser(UserModule userBean) {
        String json;
        if (null != userBean) {
            Gson gson = new Gson();
            json = gson.toJson(userBean);
        } else {
            json = "";
        }
        SharePreferencesUtils.putString(getSPKey(USER), json);
    }

    public static WxUserInfoModule getWxUser() {
        String json = SharePreferencesUtils.getString(getSPKey(WX_USER));
        Gson gson = new Gson();
        WxUserInfoModule userBean = gson.fromJson(json, WxUserInfoModule.class);
        return userBean;
    }

    public static void saveWxUser(WxUserInfoModule userInfoModule) {
        String json;
        if (null != userInfoModule) {
            Gson gson = new Gson();
            json = gson.toJson(userInfoModule);
        } else {
            json = "";
        }
        SharePreferencesUtils.putString(getSPKey(WX_USER), json);
    }

    public static boolean isLoginViaWx() {
        return SharePreferencesUtils.getBoolean(getIdKey(IS_LOGIN_VIA_WX), false);
    }

    public static void saveIsLoginViaWx(boolean login) {
        SharePreferencesUtils.putBoolean(getIdKey(IS_LOGIN_VIA_WX), login);
    }

    public static boolean isOpenGesture() {
        return SharePreferencesUtils.getBoolean(getIdKey(IS_OPEN_GESTURE), false);
    }

    public static void saveIsOpenGesture(boolean open) {
        SharePreferencesUtils.putBoolean(getIdKey(IS_OPEN_GESTURE), open);
    }

    public static String getGesture() {
        return SharePreferencesUtils.getString(getIdKey(GESTURE));
    }

    public static void saveGesture(String gesture) {
        SharePreferencesUtils.putString(getIdKey(GESTURE), gesture);
    }


//    public static String getToken() {
//        return SharePreferencesUtils.getString(TOKEN);
//    }
//
//    public static void saveToken(String token) {
//        SharePreferencesUtils.putString(TOKEN, token);
//    }

    public static void saveMd5Psw(String psw) {
        SharePreferencesUtils.putString(getIdKey(IS_OPEN_GESTURE), psw);
    }

    public static String getMd5Psw() {
        return SharePreferencesUtils.getString(getIdKey(IS_OPEN_GESTURE));
    }
}
