package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Contract.LoginContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Module.WxUserInfoModule;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl extends LoginContract.IPresenter<LoginContract.IView> {
    private LoginContract.IView mView;


    public LoginPresenterImpl(LoginContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void login(final String userName, final String password) {
        mView.showLoading("登录中", "正在登录，请稍等...");
        UserModule.login(userName, password, new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
//                mView.hideLoading();
//                if (null != response.body()) {
//                    mView.showSnackerToast(response.body().getMsg());
//                    if (response.body().isSuccess()) {
//                        SPUtiles.saveLoginPhone(userName);
//                        UserModule.saveToLocal(response.body().getData());
//                        mView.onLoginResult(true, response.body().getMsg());
//                    } else {
//                        mView.onLoginResult(false, response.body().getMsg());
//                    }
//                }
                afterLogin(response);
            }

            @Override
            public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {
                mView.hideLoading();
                mView.onLoginResult(false, t.getMessage());
            }
        });
    }

    @Override
    public void checkNull(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mView.setBtnLoginEnable(false);
        } else {
            mView.setBtnLoginEnable(true);
        }
    }

    @Override
    public void loginViaWx() {
        // send oauth request
        IWXAPI api = ((BaseApplication) BaseApplication.getInstance()).getIwxapi();
        if (null == api)
            return;
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @Override
    public void onLoginViaWx() {
        if (!SPUtiles.isLoginViaWx())
            return;
        final WxUserInfoModule wxUserInfoModule = WxUserInfoModule.getLocalUserInfo();
        mView.showLoading("登录中", "正在登录，请稍等...");
        UserModule.authLogin(wxUserInfoModule.getOpenid(), wxUserInfoModule.getNickname(), wxUserInfoModule.getHeadimgurl(),
                "1", new Callback<ResponseData<UserModule>>() {
                    @Override
                    public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
                        afterLogin(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {
                        mView.hideLoading();
                        mView.onLoginResult(false, t.getMessage());
                    }
                });
        SPUtiles.saveIsLoginViaWx(false);
    }

    @Override
    public void start() {
        mView.initLoginPhone(SPUtiles.getLoginPhone());
    }

    private void afterLogin(Response<ResponseData<UserModule>> response) {
        mView.hideLoading();
        if (response.body().isSuccess()) {
            SPUtiles.saveLoginPhone(response.body().getData().getMember().getMemberMobile());
            UserModule.saveToLocal(response.body().getData());
            mView.onLoginResult(true, response.body().getMsg());
        } else {
            switch (response.body().getCode()) {
                case 10001:
                    mView.showToast(response.body().getMsg());
                    mView.gotoBindPhone(WxUserInfoModule.getLocalUserInfo().getOpenid());
                    break;
                default:
                    mView.onLoginResult(false, response.body().getMsg());
            }

        }
    }
}
