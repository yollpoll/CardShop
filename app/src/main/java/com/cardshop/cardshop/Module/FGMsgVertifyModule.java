package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.RetrofitService.FGMsgService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * 飞鸽传书短信API
 */
public class FGMsgVertifyModule extends BaseModule {
    private String Code;//成功为0，其他请参照 API 错误代码
    private String Message;//成功为ok，其它请参考 API 错误代码
    private String SendId;//短信回执编号，为唯一识别码，用户可通过此编号获取记录详情
    private String InvalidCount;//无效号码数量
    private String SuccessCount;//成功数量
    private String BlackCount;//黑名单号码数量


    public String getSendId() {
        return SendId;
    }

    public void setSendId(String sendId) {
        SendId = sendId;
    }

    public String getInvalidCount() {
        return InvalidCount;
    }

    public void setInvalidCount(String invalidCount) {
        InvalidCount = invalidCount;
    }

    public String getSuccessCount() {
        return SuccessCount;
    }

    public void setSuccessCount(String successCount) {
        SuccessCount = successCount;
    }

    public String getBlackCount() {
        return BlackCount;
    }

    public void setBlackCount(String blackCount) {
        BlackCount = blackCount;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public static void getModule(String account, String psw, String content, String mobile,
                                 String templateId, String SignId, Callback<FGMsgVertifyModule> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit(API.FG_HEAD_URL);
        FGMsgService service = retrofit.create(FGMsgService.class);
        Call<FGMsgVertifyModule> call = service.sendMsg(account, psw, content, mobile, templateId, SignId);
        call.enqueue(callback);
    }

    @Override
    public String toString() {
        return "Code: " + Code + " Message: " + Message + " SendId: " + SendId + " InvalidCount: " + InvalidCount
                + " SuccessCount: " + SuccessCount + " BlackCount: " + BlackCount;
    }

    public static boolean ifSuccess(FGMsgVertifyModule module) {
        if (null == module)
            return false;
        return "0".equals(module.Code);
    }
}
