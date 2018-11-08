package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.RegisterContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.RxUtils;
import com.cardshop.cardshop.Utils.VertifyUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenterImpl extends RegisterContract.IPresenter<RegisterContract.IView> {
    private RegisterContract.IView mView;
    private String openId = "";

    public RegisterPresenterImpl(RegisterContract.IView mView, String openId) {
        this.mView = mView;
        this.openId = openId;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.showBack();
    }

    @Override
    public void getVertifyCode(String phone) {
        mView.showLoading("发送验证码", "验证码发送中");
        UserModule.getMsgCode(phone, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    startCountDown();
                    mView.showSnackerToast("发送成功");
                } else {
                    mView.showSnackerToast("发送失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast("发送失败");
            }
        });
    }

    @Override
    public void register(String phone, String password, String confirmPassword, String vertifyCode) {
        mView.showLoading("注册中", "正在注册");
        UserModule.register(phone, password, vertifyCode, openId, new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    mView.registerResult(true, response.body().getMsg());
                } else {
                    mView.registerResult(false, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast("访问失败");
            }
        });
    }

    @Override
    public boolean checkPhone(String phone) {
        return !TextUtils.isEmpty(phone);
    }

    @Override
    public void checkRegisterInput(String phone, String password, String confirmPassword, String vertifyCode) {
        if (TextUtils.isEmpty(phone)) {
            mView.showToast("请输入手机号");
        } else if (TextUtils.isEmpty(password)) {
            mView.showToast("请输入密码");
        } else if (TextUtils.isEmpty(confirmPassword)) {
            mView.showToast("请输入确认密码");
        } else if (TextUtils.isEmpty(vertifyCode)) {
            mView.showToast("请输入验证码");
        } else if (!vertifyPsd(password, confirmPassword)) {
            mView.showToast("两次输入的密码不同");
        } else {
            this.register(phone, password, confirmPassword, vertifyCode);
        }
    }

    public boolean checkVertifyCode(String vertifyCode) {
        return vertifyCode.equals(VertifyUtils.vertifyCode);
    }

    public boolean vertifyPsd(String psd, String confirmPsd) {
        return psd.equals(confirmPsd) ? true : false;
    }


    @Override
    public void attach(RegisterContract.IView view) {
        super.attach(view);
        RxUtils.isStopCountDown = false;
    }

    @Override
    public void detach() {
        super.detach();
        RxUtils.isStopCountDown = true;
    }

    private void startCountDown() {
        RxUtils.startCountDown(60 * 1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                if (0 == count) {
                    mView.onCountDownFinish();
                } else {
                    mView.showCountDown(count + "秒后重发");
                }
            }
        });
    }
}
