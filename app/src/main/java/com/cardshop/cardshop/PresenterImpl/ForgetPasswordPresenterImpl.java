package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Contract.ForgetPasswordContract;
import com.cardshop.cardshop.Module.FGMsgVertifyModule;
import com.cardshop.cardshop.Utils.VertifyUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenterImpl extends ForgetPasswordContract.Presenter {
    private ForgetPasswordContract.IView mView;

    public ForgetPasswordPresenterImpl(ForgetPasswordContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.showBack();
    }

    @Override
    public void getVertifyCode(String phone) {
        FGMsgVertifyModule.getModule(BaseApplication.FG_ACCOUNT, BaseApplication.FG_PSD, VertifyUtils.createVertifyCode(),
                phone, BaseApplication.FG_TemplateId, BaseApplication.FG_SignId, new Callback<FGMsgVertifyModule>() {
                    @Override
                    public void onResponse(Call<FGMsgVertifyModule> call, Response<FGMsgVertifyModule> response) {
                        if (FGMsgVertifyModule.ifSuccess(response.body())) {
                            mView.showSnackerToast("发送成功");
                        } else {
                            mView.showSnackerToast(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<FGMsgVertifyModule> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean checkVertifyCode(String code) {
        return code.equals(VertifyUtils.vertifyCode);
    }

    @Override
    public void checkInput(String phone, String vertifyCode, String psw, String confirmPsw) {
        if (TextUtils.isEmpty(phone)) {
            mView.showSnackerToast("请输入手机号");
        } else if (TextUtils.isEmpty(vertifyCode)) {
            mView.showSnackerToast("请输入验证码");
        } else if (TextUtils.isEmpty(psw)) {
            mView.showSnackerToast("请输入新密码");
        } else if (TextUtils.isEmpty(confirmPsw)) {
            mView.showSnackerToast("请输入确认密码");
        } else if (!psw.equals(confirmPsw)) {
            mView.showSnackerToast("两次输入密码不同");
        } else if (!checkVertifyCode(vertifyCode)) {
            mView.showSnackerToast("验证码错误");
        } else {
            forgetPassword(phone, psw);
        }

    }

    @Override
    public void forgetPassword(String phone, String psw) {
        mView.showSnackerToast("修改密码成功");
    }
}
