package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Contract.LoginContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.SPUtiles;

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
    public void login(final String userName, String password) {
        mView.showLoading("登录中", "正在登陆，请稍等...");
        UserModule.login(userName, password, new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
                mView.hideLoading();
                if (null != response.body()) {
                    mView.showSnackerToast(response.body().getMsg());
                    if (response.body().isSuccess()) {
                        SPUtiles.saveLoginPhone(userName);
                        UserModule.saveToLocal(response.body().getData());
                        mView.onLoginResult(true, response.body().getMsg());
                    } else {
                        mView.onLoginResult(false, response.body().getMsg());
                    }
                }
//                if ("1".equals(response.body().getCode())) {
//                    mView.onLoginResult(false, "用户名密码错误");
//                } else {
//                    SPUtiles.saveLoginPhone(userName);
//                    UserModule.saveToLocal(response.body().getData());
//                    mView.onLoginResult(true, "登录成功");
//                }
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
    public void start() {
        mView.initLoginPhone(SPUtiles.getLoginPhone());
    }
}
