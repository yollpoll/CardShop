package com.cardshop.cardshop.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Module.WxTokenModule;
import com.cardshop.cardshop.Module.WxUserInfoModule;
import com.cardshop.cardshop.Utils.LogUtil;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.framework.Utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ((BaseApplication) BaseApplication.getInstance()).getIwxapi();
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                LogUtil.Log("参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtil.Log("baseResp:" + baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode);
        if (baseResp.errCode == 0) {
            //用户同意
            getToken(((SendAuth.Resp) baseResp).code + "");
        } else if (baseResp.errCode == -4) {
            //用户拒绝
            ToastUtils.showShort("获取信息失败");
            WXEntryActivity.this.finish();
        } else if (baseResp.errCode == -2) {
            //用户取消
            WXEntryActivity.this.finish();
        }

    }

    private void getToken(String code) {
        WxTokenModule.getToken(code, new Callback<WxTokenModule>() {
            @Override
            public void onResponse(Call<WxTokenModule> call, Response<WxTokenModule> response) {
                if (null == response.body()) {
                    ToastUtils.showShort("授权失败");
                    WXEntryActivity.this.finish();
                    return;
                }
                getUserInfo(response.body().getAccess_token(), response.body().getOpenid());
//                refreshToken(response.body().getRefresh_token());
            }

            @Override
            public void onFailure(Call<WxTokenModule> call, Throwable t) {

            }
        });
    }

    private void refreshToken(String refreshToken) {
        WxTokenModule.refreshToken(refreshToken, new Callback<WxTokenModule>() {
            @Override
            public void onResponse(Call<WxTokenModule> call, Response<WxTokenModule> response) {
                if (null == response.body()) {
                    ToastUtils.showShort("授权失败");
                    WXEntryActivity.this.finish();
                    return;
                }
                getUserInfo(response.body().getAccess_token(), response.body().getOpenid());
            }

            @Override
            public void onFailure(Call<WxTokenModule> call, Throwable t) {

            }
        });
    }

    public void getUserInfo(String token, String openId) {
        WxUserInfoModule.getWxUserInfo(token, openId, new Callback<WxUserInfoModule>() {
            @Override
            public void onResponse(Call<WxUserInfoModule> call, Response<WxUserInfoModule> response) {
                if (null == response.body()) {
                    ToastUtils.showShort("获取用户信息失败");
                    return;
                }
                WxUserInfoModule.saveToLocal(response.body());
                SPUtiles.saveIsLoginViaWx(true);
                WXEntryActivity.this.finish();
            }

            @Override
            public void onFailure(Call<WxUserInfoModule> call, Throwable t) {

            }
        });
    }

    private void login(String openId, String nickName, String avatar) {
        UserModule.authLogin(openId, nickName, avatar, "1", new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {

            }

            @Override
            public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {

            }
        });
    }
}
